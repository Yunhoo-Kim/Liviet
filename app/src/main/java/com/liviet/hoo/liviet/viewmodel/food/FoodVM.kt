package com.liviet.hoo.liviet.viewmodel.food

import com.liviet.hoo.liviet.base.BaseViewModel
import com.liviet.hoo.liviet.model.nutrition.Food
import com.liviet.hoo.liviet.model.nutrition.FoodRepository
import com.liviet.hoo.liviet.ui.food.FoodListAdapter
import io.reactivex.Observable
import javax.inject.Inject


@Suppress("unused")
class FoodVM @Inject constructor(private val foodRepository: FoodRepository): BaseViewModel() {

    val foodListAdapter: FoodListAdapter by lazy {
        FoodListAdapter()
    }

    init {

    }

    fun getFoods(): Observable<List<Food>>{
        foodListAdapter.updateFoodList(foodRepository.getFoods().blockingFirst())
        return foodRepository.getFoods()
    }
}