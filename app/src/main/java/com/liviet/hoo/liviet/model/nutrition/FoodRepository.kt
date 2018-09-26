package com.liviet.hoo.liviet.model.nutrition

import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.model.SharedPreferenceHelper
import io.reactivex.Observable
import javax.inject.Inject


@Suppress("unused")
class FoodRepository @Inject constructor(private val foodDao: FoodDao, private val sharedPreferenceHelper: SharedPreferenceHelper){

    init {
        initFoods()
//        this.getFoods().subscribe {
//            if(it.isEmpty())
//                this.initFoods()
//        }
    }
    fun saveFoods(foods: List<Food>): Observable<List<Food>> {
        for(f in foods) {
            foodDao.insert(f)
        }
        return Observable.just(foods)
    }

    private fun initFoods(){
        foodDao.deleteAll()

        foodDao.insert(Food(name = "사과", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
        ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.apple))

        foodDao.insert(Food(name = "고구마", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.sweet_potato))

        foodDao.insert(Food(name = "브로콜리", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.broccoli))

        foodDao.insert(Food(name = "파프리카", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.paprika))
        foodDao.insert(Food(name = "사과1", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.apple))

        foodDao.insert(Food(name = "고구마1", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.sweet_potato))

        foodDao.insert(Food(name = "브로콜리1", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.broccoli))

        foodDao.insert(Food(name = "파프리카1", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.paprika))

        foodDao.insert(Food(name = "사과2", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.apple))

        foodDao.insert(Food(name = "고구마2", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.sweet_potato))

        foodDao.insert(Food(name = "브로콜리2", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.broccoli))

        foodDao.insert(Food(name = "파프리카2", amount = 100, carbon_hydrate = 10f, cal = 10f, fat = 10f
                ,image_url = "aaa.png", measure = "g", na = 10.0f, protein = 10.0f, resource_id = R.drawable.paprika))
    }

    fun getFoods(): Observable<List<Food>> = Observable.just(foodDao.getFoods())
    fun getFoodById(id: Long): Observable<Food> = Observable.just(foodDao.getFoodById(id))
}