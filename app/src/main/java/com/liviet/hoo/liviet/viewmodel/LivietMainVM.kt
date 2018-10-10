package com.liviet.hoo.liviet.viewmodel

import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseViewModel
import com.liviet.hoo.liviet.model.nutrition.Food
import com.liviet.hoo.liviet.model.nutrition.FoodRepository
import com.liviet.hoo.liviet.ui.food.FoodListAdapter
import com.liviet.hoo.liviet.ui.food.MainDateListAdapter
import com.liviet.hoo.liviet.ui.food.MainFoodListAdapter
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject


@Suppress("unused")
class LivietMainVM @Inject constructor(private val foodRepository: FoodRepository): BaseViewModel() {

    val dateListAdapter: MainDateListAdapter by lazy {
        MainDateListAdapter()
    }

    val mainFoodListAdapter: MainFoodListAdapter by lazy {
        MainFoodListAdapter()
    }

    init {

    }

//    fun saveDiet(): Observable<Food> {
//
//    }
//    fun getFoods(): Observable<List<Food>>{
//        foodListAdapter.updateFoodList(foodRepository.getFoods().blockingFirst())
//        return foodRepository.getFoods()
//    }

    fun addFoods(){
        var foodList: MutableList<Food> = mutableListOf()
        foodList.addAll(foodRepository.getFoods().blockingFirst())

        foodList.add(Food(name = "plus", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.apple))
        foodList.add(Food(name = "사과", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.apple))
        foodList.add(Food(name = "고구마", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.sweet_potato))
        foodList.add(Food(name = "ad", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.broccoli))
        foodList.add(Food(name = "사과", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.apple))
        foodList.add(Food(name = "고구마", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.sweet_potato))
        foodList.add(Food(name = "브로콜리", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.broccoli))
        foodList.add(Food(name = "ad", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.broccoli))
        foodList.add(Food(name = "사과", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.apple))
        foodList.add(Food(name = "고구마", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.sweet_potato))
        foodList.add(Food(name = "브로콜리", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.broccoli))
        foodList.add(Food(name = "ad", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.broccoli))
        foodList.add(Food(name = "사과", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.apple))
        foodList.add(Food(name = "고구마", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.sweet_potato))
        foodList.add(Food(name = "브로콜리", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.broccoli))

        mainFoodListAdapter.updateFoodList(foodList)
    }
    fun initDate(){

        var calendar:Calendar = Calendar.getInstance()
        var dateList = mutableListOf<Date>(calendar.time)

        for (i in 1..7){
            calendar.add(Calendar.DAY_OF_WEEK, 1)
            dateList.add(calendar.time)
        }

        dateListAdapter.updateDateList(dateList)
    }
}