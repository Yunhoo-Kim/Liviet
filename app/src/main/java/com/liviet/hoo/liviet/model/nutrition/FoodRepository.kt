package com.liviet.hoo.liviet.model.nutrition

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.liviet.hoo.liviet.model.SharedPreferenceHelper
import com.liviet.hoo.liviet.model.liviet.VersionsDao
import com.liviet.hoo.liviet.utils.FIREBASE_DB_URL
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


@Suppress("unused")
class FoodRepository @Inject constructor(private val foodDao: FoodDao,
                                         private val versionsDao: VersionsDao,
                                         private val firebaseStore: FirebaseFirestore,
                                         private val sharedPreferenceHelper: SharedPreferenceHelper){

    val mCompositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    init {
        initFoods()
    }

    fun loadFoodsFromStore(): Observable<List<Food>> {
        return Observable.create { emitter ->
            firebaseStore.collection(FIREBASE_DB_URL).get().addOnCompleteListener { res ->
                if (res.isSuccessful) {
                    for (doc in res.result!!) {
                        foodDao.insert(Food(
                                id = doc.data["id"].toString().toLong(),
                                amount = doc.data["amount"].toString().toInt(),
                                name = doc.data["name"].toString(),
                                carbonHydrate = doc.data["carbonHydrate"].toString().toDouble(),
                                protein = doc.data["protein"].toString().toDouble(),
                                fat = doc.data["fat"].toString().toDouble(),
                                cal = doc.data["cal"].toString().toInt(),
                                measure = doc.data["measure"].toString(),
                                imageUrl = doc.data["imageUrl"].toString()))
                    }
                    emitter.onNext(foodDao.getFoods())
                }else{
                    Log.d("LoadFood", "error")
                }
            }.addOnFailureListener {err ->
                Log.d("error", err.toString())
            }
        }
    }

    private fun loadFoods(): Observable<List<Food>>{
        return Observable.fromCallable { foodDao.getFoods() }
                .concatMap {
                    if(it.isEmpty())
                        loadFoodsFromStore()
                    else {
                        Observable.just(it)
                    }
                }
    }

    fun saveFoods(foods: List<Food>): Observable<List<Food>> {
        for(f in foods) {
            foodDao.insert(f)
        }
        return Observable.just(foods)
    }

    fun saveFood(food: Food)  = foodDao.insert(food)


    private fun initFoods(){
//        mCompositeDisposable.dispose()
//        mCompositeDisposable.add(loadFoods()
//                .subscribe({
//                }, {
//                }, {
//                }))
    }

    fun getFoods(): Observable<List<Food>> = Observable.just(foodDao.getFoods())
    fun getFoodById(id: Long): Observable<Food> = Observable.just(foodDao.getFoodById(id))
    fun searchFood(query: String): List<Food> = foodDao.searchFoodByName("%$query%")

}