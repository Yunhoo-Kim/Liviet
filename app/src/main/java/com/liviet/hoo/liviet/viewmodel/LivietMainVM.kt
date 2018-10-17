package com.liviet.hoo.liviet.viewmodel

import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseViewModel
import com.liviet.hoo.liviet.model.nutrition.Diet
import com.liviet.hoo.liviet.model.nutrition.DietRepository
import com.liviet.hoo.liviet.model.nutrition.Food
import com.liviet.hoo.liviet.model.nutrition.FoodRepository
import com.liviet.hoo.liviet.ui.food.DietListAdapter
import com.liviet.hoo.liviet.ui.food.FoodListAdapter
import com.liviet.hoo.liviet.ui.food.MainDateListAdapter
import com.liviet.hoo.liviet.ui.food.MainFoodListAdapter
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject


@Suppress("unused")
class LivietMainVM @Inject constructor(private val foodRepository: FoodRepository, private val dietRepository: DietRepository): BaseViewModel() {

//    val dateListAdapter: MainDateListAdapter by lazy {
//        MainDateListAdapter()
//    }

    val mainFoodListAdapter: MainFoodListAdapter by lazy {
        MainFoodListAdapter()
    }
//    val dietListAdapter: DietListAdapter by lazy {
//        DietListAdapter()
//    }

    init {

    }

//    fun saveDiet(): Observable<Food> {
//
//    }
//    fun getFoods(): Observable<List<Food>>{
//        foodListAdapter.updateFoodList(foodRepository.getFoods().blockingFirst())
//        return foodRepository.getFoods()
//    }

//    fun getDiet(date: Date): Observable<List<Diet>>{
////        foodList.add(Food(name = "plus", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
////                ,image_url = "", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.apple))
//
//        val dietList = dietRepository.getDietsByDate(date).blockingFirst()
//        val mDietList: MutableList<Pair<Diet, Food>> = mutableListOf()
//
//        for(d in dietList){
//            val pair = Pair<Diet, Food>(d, foodRepository.getFoodById(d.foodId).blockingFirst() )
//            mDietList.add(pair)
//        }
//
//        dietListAdapter.updateDietList(mDietList)
//        return Observable.just(dietList)
//    }
//
//    fun getDiets(): Observable<List<Diet>>{
//        val dietList = dietRepository.getDiets().blockingFirst()
//        val mDietList: MutableList<Pair<Diet, Food>> = mutableListOf()
//
//        for(d in dietList){
//            val pair = Pair<Diet, Food>(d, foodRepository.getFoodById(d.foodId).blockingFirst() )
//            mDietList.add(pair)
//        }
//        dietListAdapter.updateDietList(mDietList)
//        return Observable.just(dietList)
//    }

    fun addFoods(){
        var foodList: MutableList<Food> = mutableListOf()
    }
    fun initDate(){
        var calendar:Calendar = Calendar.getInstance()
        for (i in 1..7){
            calendar.add(Calendar.DAY_OF_WEEK, 1)
        }
    }
}