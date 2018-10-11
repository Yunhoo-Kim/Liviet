package com.liviet.hoo.liviet.viewmodel.food

import android.util.Log
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseViewModel
import com.liviet.hoo.liviet.model.nutrition.Diet
import com.liviet.hoo.liviet.model.nutrition.DietRepository
import com.liviet.hoo.liviet.model.nutrition.Food
import com.liviet.hoo.liviet.model.nutrition.FoodRepository
import com.liviet.hoo.liviet.ui.food.DietListAdapter
import com.liviet.hoo.liviet.ui.food.FoodListAdapter
import com.liviet.hoo.liviet.ui.food.MainDateListAdapter
import com.liviet.hoo.liviet.utils.Utils
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject


@Suppress("unused")
class DietVM @Inject constructor(private val dietRepository: DietRepository, private val foodRepository: FoodRepository): BaseViewModel() {

    var cDate: Date = Utils.makeCalendarToDate(Calendar.getInstance())

    val foodListAdapter: FoodListAdapter by lazy {
        FoodListAdapter()
    }

    val dateListAdapter: MainDateListAdapter by lazy {
        MainDateListAdapter(this)
    }

    val dietListAdapter: DietListAdapter by lazy {
        DietListAdapter()
    }

    init {

    }

    fun insertDiet(diet: Diet) {
        diet.date = cDate
        dietRepository.saveDiet(diet)
    }

    fun getDiet(date: Date): Observable<List<Diet>>{
        Log.d("Date", date.toString())

        val dietList = dietRepository.getDietsByDate(date).blockingFirst()
        Log.d("Date", dietList.size.toString())
        val mDietList: MutableList<Pair<Diet, Food>> = mutableListOf()


        mDietList.add(Pair(Diet(amount = 0, foodId = 1, date = cDate), Food(name = "plus", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.apple)))

        for(d in dietList){
            Log.d("Date", d.amount.toString())
            val pair = Pair<Diet, Food>(d, getFoodById(d.foodId).blockingFirst() )
            mDietList.add(pair)
        }

        dietListAdapter.updateDietList(mDietList)
        return Observable.just(dietList)
    }

    fun getDiets(): Observable<List<Diet>> = this.getDiet(cDate)

    fun getFoodById(id: Long): Observable<Food> = foodRepository.getFoodById(id)

    fun initDate(){

        var calendar:Calendar = Calendar.getInstance()
        val dateList = mutableListOf<Calendar>()

        for (i in -2..7){
            calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_WEEK, i)
            dateList.add(calendar)
        }

        dateListAdapter.updateDateList(dateList)
    }
}

