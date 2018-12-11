package com.liviet.hoo.liviet.viewmodel.food

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IValueFormatter
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseViewModel
import com.liviet.hoo.liviet.model.liviet.VersionsRepository
import com.liviet.hoo.liviet.model.nutrition.Diet
import com.liviet.hoo.liviet.model.nutrition.DietRepository
import com.liviet.hoo.liviet.model.nutrition.Food
import com.liviet.hoo.liviet.model.nutrition.FoodRepository
import com.liviet.hoo.liviet.model.user.UserRepository
import com.liviet.hoo.liviet.ui.food.DietListAdapter
import com.liviet.hoo.liviet.ui.food.FoodListAdapter
import com.liviet.hoo.liviet.ui.food.MainDateListAdapter
import com.liviet.hoo.liviet.utils.Utils
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject


@Suppress("unused")
class DietVM @Inject constructor(private val dietRepository: DietRepository,
                                 private val foodRepository: FoodRepository,
                                 private val versionsRepository: VersionsRepository,
                                 private val userRepository: UserRepository): BaseViewModel() {

    private val dateList = mutableListOf<Calendar>()

    lateinit var calDataSet: LineDataSet
    lateinit var carbonDataSet: LineDataSet
    lateinit var proteinDataSet: LineDataSet
    lateinit var fatDataSet: LineDataSet
    lateinit var sCalDataSet: LineDataSet
    lateinit var sCarbonDataSet: LineDataSet
    lateinit var sProteinDataSet: LineDataSet
    lateinit var sFatDataSet: LineDataSet

    var cDate: Date = Utils.makeCalendarToDate(Calendar.getInstance())
    var tDate: Date = Utils.makeCalendarToDate(Calendar.getInstance())
    var cal: MutableLiveData<String> = MutableLiveData()
    var chartData: MutableLiveData<BarData> = MutableLiveData()
    var weeklyData: MutableLiveData<LineData> = MutableLiveData()
    var todayCarbon: MutableLiveData<String> = MutableLiveData()
    var todayProtein: MutableLiveData<String> = MutableLiveData()
    var todayFat: MutableLiveData<String> = MutableLiveData()
    var todayCal: MutableLiveData<String> = MutableLiveData()
    var todayCarbonPercent: MutableLiveData<String> = MutableLiveData()
    var todayProteinPercent: MutableLiveData<String> = MutableLiveData()
    var todayFatPercent: MutableLiveData<String> = MutableLiveData()
    var todayCalPercent: MutableLiveData<String> = MutableLiveData()
    lateinit var weeklyDateData: MutableList<String>

    val foodListAdapter: FoodListAdapter by lazy {
        FoodListAdapter()
    }

    val dateListAdapter: MainDateListAdapter by lazy {
        MainDateListAdapter(this)
    }

    val dietListAdapter: DietListAdapter by lazy {
        DietListAdapter(this)
    }

    init {
        versionsRepository.checkVersion()
        cal.value = "0Kcal"
    }

    fun insertDiet(diet: Diet) {
        diet.date = cDate
        dietRepository.saveDiet(diet)
    }

    fun getDiet(date: Date): Observable<List<Diet>>{
        val dietList = dietRepository.getDietsByDate(date).blockingFirst()
        val mDietList: MutableList<Pair<Diet, Food>> = mutableListOf()

        mDietList.add(Pair(Diet(amount = 0, foodId = 1, date = cDate), Food(name = "plus", amount = 100, carbonHydrate = 10.0, cal = 10, fat = 10.0
                ,imageUrl = "", measure = "g", protein = 10.0)))

        var kcal = 0

        for(d in dietList){
            val pair = Pair<Diet, Food>(d, getFoodById(d.foodId).blockingFirst() )
            kcal += pair.first.amount * pair.second.cal / pair.second.amount
            mDietList.add(pair)
        }

        cal.value = "${kcal}Kcal"
        dietListAdapter.updateDietList(mDietList)
        return Observable.just(dietList)
    }

    fun getDiets(): Observable<List<Diet>> = this.getDiet(cDate)

    fun deleteDiet(id: Long) {
        dietRepository.delDiet(id)
        getDiets()
    }

    fun getDietById(id: Long): Observable<Diet> = dietRepository.getDietById(id)
    fun getFoodById(id: Long): Observable<Food> = foodRepository.getFoodById(id)

    fun initDate(){
        if(dateList.isNotEmpty())
            return

        for (i in -5..3){
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, i)
            dateList.add(calendar)
        }
        dateListAdapter.initDateList(dateList)
    }

    fun updateDate(date: Date){
        var i = 0
        var j = 0

        for(d in dateList){
            if(d.time == date)
                i = dateList.indexOf(d)
            if(d.time == cDate)
                j = dateList.indexOf(d)
        }

        dateListAdapter.updateDateList(dateList, i, j)
    }

    private fun getStandardData(): MutableList<Float>{
        val entries = mutableListOf<Float>()
        val user = userRepository.getUser().blockingFirst()
        val bM = Utils.getBasalMetabolism(user.weight, user.height, user.age, user.sex)
        val kcal = Utils.getKcal(bM, user.life_type)
        val carbon = Utils.getCarbonHydrate(user.weight, user.height,user.age, user.sex, user.life_type)
        val fat = Utils.getFat(user.weight, user.height, user.age, user.sex,  user.life_type)
        val protein = Utils.getProtein(user.weight, user.height, user.age, user.sex,  user.life_type)

        Log.d("Nutrition1", "$carbon $fat $protein")

        entries.add(kcal.toFloat())
        entries.add(carbon.toFloat())
        entries.add(protein.toFloat())
        entries.add(fat.toFloat())

        return entries
    }


    private fun getWeeksDietInfo(context: Context): LineData {
        val user = userRepository.getUser().blockingFirst()
        val bM = Utils.getBasalMetabolism(user.weight, user.height, user.age, user.sex)
        val sKcal = Utils.getKcal(bM, user.life_type).toFloat()
        val sCarbon = Utils.getCarbonHydrate(user.weight, user.height,user.age, user.sex, user.life_type).toFloat()
        val sFat = Utils.getFat(user.weight, user.height, user.age, user.sex,  user.life_type).toFloat()
        val sProtein = Utils.getProtein(user.weight, user.height, user.age, user.sex,  user.life_type).toFloat()

        weeklyDateData = mutableListOf()

        val setEntries = mutableListOf<LineDataSet>()
        val calEntries = mutableListOf<Entry>()
        val carbonEntries = mutableListOf<Entry>()
        val proteinEntries = mutableListOf<Entry>()
        val fatEntries = mutableListOf<Entry>()

        val sCalEntries = mutableListOf<Entry>()
        val sCarbonEntries = mutableListOf<Entry>()
        val sProteinEntries = mutableListOf<Entry>()
        val sFatEntries = mutableListOf<Entry>()

        for(i in -5..0){
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, i)
            val date = Utils.makeCalendarToDate(calendar)
            var carbon = 0.0
            var fat = 0.0
            var protein = 0.0
            var kcal = 0
            val diets = getDiet(date).blockingFirst()

            for(diet in diets){
                val food = getFoodById(diet.foodId).blockingFirst()
                carbon += (food.carbonHydrate / food.amount) * diet.amount
                fat += (food.fat / food.amount) * diet.amount
                protein += (food.protein / food.amount) * diet.amount
                kcal += diet.amount * food.cal / food.amount
            }

            val index = (i + 5).toFloat()
            weeklyDateData.add(index.toInt(), "${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.DATE)}")
            sCalEntries.add(Entry(index, sKcal))
            sCarbonEntries.add(Entry(index, sCarbon))
            sFatEntries.add(Entry(index, sFat))
            sProteinEntries.add(Entry(index, sProtein))

            calEntries.add(Entry(index, kcal.toFloat()))
            carbonEntries.add(Entry(index, carbon.toFloat()))
            proteinEntries.add(Entry(index, protein.toFloat()))
            fatEntries.add(Entry(index, fat.toFloat()))
        }

        calDataSet = LineDataSet(calEntries, "").apply {
            color = context.getColor(R.color.colorPrimaryBlue)
            lineWidth = 3f
            valueTextSize = 10f
        }


        carbonDataSet = LineDataSet(carbonEntries, "").apply {
            color = context.getColor(R.color.colorPrimaryDark)
            lineWidth = 3f
            valueTextSize = 10f
        }

        proteinDataSet = LineDataSet(proteinEntries, "").apply {
            color = context.getColor(R.color.colorPrimary)
            lineWidth = 3f
            valueTextSize = 10f
        }

        fatDataSet = LineDataSet(fatEntries, "").apply {
            lineWidth = 3f
            valueTextSize = 10f
        }

        sCalDataSet = LineDataSet(sCalEntries, "").apply {
            color = context.getColor(R.color.colorPrimaryGray)
            lineWidth = 3f
            valueTextSize = 10f
        }

        sCarbonDataSet = LineDataSet(sCarbonEntries, "").apply {
            color = context.getColor(R.color.colorPrimaryGray)
            lineWidth = 3f
            valueTextSize = 10f
        }

        sProteinDataSet = LineDataSet(sProteinEntries, "").apply {
            color = context.getColor(R.color.colorPrimaryGray)
            lineWidth = 3f
            valueTextSize = 10f
        }

        sFatDataSet = LineDataSet(sFatEntries, "").apply {
            color = context.getColor(R.color.colorPrimaryGray)
            lineWidth = 3f
            valueTextSize = 10f
        }

        return LineData(setEntries.toList())
    }

    private fun getTodayDietInfo(): BarDataSet {
        val todayDiets = getDiet(tDate).blockingFirst()
        val entries = mutableListOf<BarEntry>()
        var carbon = 0.0
        var fat = 0.0
        var protein = 0.0
        var kcal = 0

        val user = userRepository.getUser().blockingFirst()
        val bM = Utils.getBasalMetabolism(user.weight, user.height, user.age, user.sex)
        val sKcal = Utils.getKcal(bM, user.life_type)
        val sCarbon = Utils.getCarbonHydrate(user.weight, user.height,user.age, user.sex, user.life_type)
        val sFat = Utils.getFat(user.weight, user.height, user.age, user.sex,  user.life_type)
        val sProtein = Utils.getProtein(user.weight, user.height, user.age, user.sex,  user.life_type)

        for(diet in todayDiets){
            val food = getFoodById(diet.foodId).blockingFirst()
            carbon += (food.carbonHydrate / food.amount) * diet.amount
            fat += (food.fat / food.amount) * diet.amount
            protein += (food.protein / food.amount) * diet.amount
            kcal += diet.amount * food.cal / food.amount
        }

        todayCal.postValue("$kcal")
        todayCarbon.postValue("%.1fg".format(carbon))
        todayProtein.postValue("%.1fg".format(protein))
        todayFat.postValue("%.1fg".format(fat))

        todayCalPercent.postValue("${((kcal.toFloat() / sKcal.toFloat()) * 100).toInt()}%")
        todayCarbonPercent.postValue("${((carbon.toFloat() / sCarbon.toFloat()) * 100).toInt()}%")
        todayProteinPercent.postValue("${((protein.toFloat() / sProtein.toFloat()) * 100).toInt()}%")
        todayFatPercent.postValue("${((fat.toFloat() / sFat.toFloat()) * 100).toInt()}%")

//        entries.add(BarEntry(0f, ((kcal.toFloat() / sKcal.toFloat()) * 100).toInt().toFloat()))
//        entries.add(BarEntry(1f, ((carbon.toFloat() / sCarbon.toFloat()) * 100).toInt().toFloat()))
//        entries.add(BarEntry(2f, ((protein.toFloat() / sProtein.toFloat()) * 100).toInt().toFloat()))
//        entries.add(BarEntry(3f, ((fat.toFloat() / sFat.toFloat()) * 100).toInt().toFloat()))
        return BarDataSet(entries, "")
    }

    fun loadCharData(context: Context){

//        val todayDiet = getTodayDietInfo().apply {
//            label = ""
//            valueTextSize = 15.2f
//            valueFormatter = IValueFormatter { value, _, _, _ ->
//                return@IValueFormatter "${value.toInt()}%"
//            }
//            barBorderColor = context.getColor(R.color.colorPrimary)
//            barBorderWidth = 2f
//            color = context.getColor(R.color.colorPrimaryDark)
//        }
        getTodayDietInfo()

//        chartData.value = BarData(todayDiet).apply {
//            barWidth = 0.5f
//        }
        getWeeksDietInfo(context)
    }

    fun loadLineChart(pos: Int){
        when(pos){
            0-> weeklyData.value = LineData(listOf(sCalDataSet, calDataSet))
            1-> weeklyData.value = LineData(listOf(sCarbonDataSet, carbonDataSet))
            2-> weeklyData.value = LineData(listOf(sProteinDataSet, proteinDataSet))
            else -> weeklyData.value = LineData(listOf(sFatDataSet, fatDataSet))
        }
    }
}

