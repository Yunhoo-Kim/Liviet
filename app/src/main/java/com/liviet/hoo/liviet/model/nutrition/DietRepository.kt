package com.liviet.hoo.liviet.model.nutrition

import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.model.SharedPreferenceHelper
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject


@Suppress("unused")
class DietRepository @Inject constructor(private val dietDao: DietDao, private val sharedPreferenceHelper: SharedPreferenceHelper){

    init {
        initFoods()
//        this.getFoods().subscribe {
//            if(it.isEmpty())
//                this.initFoods()
//        }
    }

    fun saveDiet(diet: Diet)  = dietDao.insert(diet)

    private fun initFoods(){

    }

    fun getDiets(): Observable<List<Diet>> = Observable.just(dietDao.getDiets())
    fun getDietsByDate(date: Date): Observable<List<Diet>> = Observable.just(dietDao.getDietByDate(date))
    fun getDietById(id: Long): Observable<Diet> = Observable.just(dietDao.getDietById(id))
    fun delDiet(id: Long) = dietDao.delDiet(id)
}