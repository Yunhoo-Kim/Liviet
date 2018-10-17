package com.liviet.hoo.liviet.viewmodel.food

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
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

    var cDate: Date = Utils.makeCalendarToDate(Calendar.getInstance())
    var tDate: Date = Utils.makeCalendarToDate(Calendar.getInstance())

    private val dateList = mutableListOf<Calendar>()
    var chartData: MutableLiveData<RadarData> = MutableLiveData()

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

        for(d in dietList){
            val pair = Pair<Diet, Food>(d, getFoodById(d.foodId).blockingFirst() )
            mDietList.add(pair)
        }

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

        for (i in -2..4){
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

    private fun getStandardDietRadar(): RadarDataSet{
        val entries = mutableListOf<RadarEntry>()
        val user = userRepository.getUser().blockingFirst()
        val bM = Utils.getBasalMetabolism(user.weight, user.height, user.age, user.sex)
        val kcal = Utils.getKcal(bM, user.life_type)
        val carbon = Utils.getCarbonHydrate(user.weight, user.height,user.age, user.sex, user.life_type)
        val fat = Utils.getFat(user.weight, user.height, user.age, user.sex,  user.life_type)
        val protein = Utils.getProtein(user.weight, user.height, user.age, user.sex,  user.life_type)

        Log.d("Nutrition1", "$carbon $fat $protein")

        entries.add(RadarEntry(carbon.toFloat() / 4, 0))
        entries.add(RadarEntry(protein.toFloat(), 1))
        entries.add(RadarEntry(fat.toFloat(), 2))

        return RadarDataSet(entries, "")
    }

    private fun getTodayDietInfo(): RadarDataSet {
        val todayDiets = getDiet(tDate).blockingFirst()
        val entries = mutableListOf<RadarEntry>()
        var carbon = 0.0
        var fat = 0.0
        var protein = 0.0

        for(diet in todayDiets){
            val food = getFoodById(diet.foodId).blockingFirst()
            carbon += (food.carbonHydrate / food.amount) * diet.amount
            fat += (food.fat / food.amount) * diet.amount
            protein += (food.protein / food.amount) * diet.amount
        }

        Log.d("Nutrition", "$carbon $fat $protein")

        entries.add(RadarEntry(carbon.toFloat(), 0))
        entries.add(RadarEntry(protein.toFloat(), 1))
        entries.add(RadarEntry(fat.toFloat(), 2))
        return RadarDataSet(entries, "")
    }

    fun loadCharData(context: Context){

        chartData.value = RadarData(listOf())
        val standardDiet = getStandardDietRadar().apply {
            this.label = context.getString(R.string.suggested_daily_taking)
            this.color = context.getColor(R.color.colorPrimaryBlue)
            this.fillColor = context.getColor(R.color.colorPrimaryBlue)
            this.fillAlpha = 100
            this.setDrawFilled(true)
            this.setDrawHighlightIndicators(false)
        }
        val todayDiet = getTodayDietInfo().apply {
            this.label = context.getString(R.string.today_taking)
            this.color = context.getColor(R.color.colorPrimary)
            this.fillColor = context.getColor(R.color.colorPrimaryDark)
            this.fillAlpha = 100
            this.setDrawFilled(true)
            this.setDrawHighlightIndicators(false)
        }
        chartData.value = RadarData(listOf(standardDiet, todayDiet)).apply {
            this.setDrawValues(false)
        }
    }
}

