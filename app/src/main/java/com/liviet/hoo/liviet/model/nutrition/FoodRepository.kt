package com.liviet.hoo.liviet.model.nutrition

import com.liviet.hoo.liviet.model.SharedPreferenceHelper
import io.reactivex.Observable
import javax.inject.Inject


@Suppress("unused")
class FoodRepository @Inject constructor(private val foodDao: FoodDao, private val sharedPreferenceHelper: SharedPreferenceHelper){

    init {
        this.getFoods().subscribe {
            if(it.isEmpty())
                this.initFoods()
        }
    }
    fun saveFoods(foods: List<Food>): Observable<List<Food>> {
        for(f in foods) {
            foodDao.insert(f)
        }
        return Observable.just(foods)
    }

    private fun initFoods(){
        foodDao.insert(Food(name = "Apple", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
        ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f))
    }

    fun getFoods(): Observable<List<Food>> = Observable.just(foodDao.getFoods())
    fun getFoodById(id: Long): Observable<Food> = Observable.just(foodDao.getFoodById(id))
}