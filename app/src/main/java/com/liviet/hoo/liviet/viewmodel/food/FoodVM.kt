package com.liviet.hoo.liviet.viewmodel.food

import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseViewModel
import com.liviet.hoo.liviet.model.nutrition.Food
import com.liviet.hoo.liviet.model.nutrition.FoodRepository
import com.liviet.hoo.liviet.ui.food.AddFoodListAdapter
import com.liviet.hoo.liviet.ui.food.FoodListAdapter
import io.reactivex.Observable
import javax.inject.Inject


@Suppress("unused")
class FoodVM @Inject constructor(private val foodRepository: FoodRepository): BaseViewModel() {

    var measureEntries: MutableList<String> = mutableListOf<String>().apply {
//        R.array.select_measure
        this.add("g")
        this.add("mg")
        this.add("l")
        this.add("ml")
        this.add("개")
        this.add("공기")
    }

    val foodListAdapter: FoodListAdapter by lazy {
        FoodListAdapter()
    }

    val addFoodListAdapter: AddFoodListAdapter by lazy {
        AddFoodListAdapter()
    }

    init {

    }

    fun insertFood(food:Food) = foodRepository.saveFood(food)

    fun getFood(id: Long): Observable<Food>{
        return foodRepository.getFoodById(id)
    }

    fun getFoods(): Observable<List<Food>>{
        foodListAdapter.updateFoodList(foodRepository.getFoods().blockingFirst())
        return foodRepository.getFoods()
    }

    fun loadFoodOnAdd(): Observable<List<Food>> {
        addFoodListAdapter.updateFoodList(foodRepository.getFoods().blockingFirst())
        return foodRepository.getFoods()
    }
}