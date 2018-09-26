package com.liviet.hoo.liviet.viewmodel

import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseViewModel
import com.liviet.hoo.liviet.model.nutrition.Food
import com.liviet.hoo.liviet.model.nutrition.FoodRepository
import com.liviet.hoo.liviet.ui.food.FoodListAdapter
import com.liviet.hoo.liviet.ui.food.MainDateListAdapter
import com.liviet.hoo.liviet.ui.food.MainFoodListAdapter
import io.reactivex.Observable
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

//    fun getFoods(): Observable<List<Food>>{
//        foodListAdapter.updateFoodList(foodRepository.getFoods().blockingFirst())
//        return foodRepository.getFoods()
//    }

    fun addFoods(){
        var foodList: MutableList<Food> = mutableListOf()

        foodList.add(Food(name = "plus", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.apple))
        foodList.add(Food(name = "사과", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.apple))
        foodList.add(Food(name = "고구마", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.sweet_potato))
        foodList.add(Food(name = "ad", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.broccoli))

        foodList.add(Food(name = "사과", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.apple))
        foodList.add(Food(name = "고구마", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.sweet_potato))
        foodList.add(Food(name = "브로콜리", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.broccoli))
        foodList.add(Food(name = "사과", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.apple))
        foodList.add(Food(name = "고구마", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.sweet_potato))
        foodList.add(Food(name = "브로콜리", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.broccoli))
        foodList.add(Food(name = "사과", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.apple))
        foodList.add(Food(name = "고구마", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.sweet_potato))
        foodList.add(Food(name = "브로콜리", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.broccoli))
        mainFoodListAdapter.updateFoodList(foodList)
    }
    fun initDate(){
        var dateList = mutableListOf("27", "28", "29", "30", "31", "1", "2", "3", "4", "5")
        dateListAdapter.updateDateList(dateList)
    }
}