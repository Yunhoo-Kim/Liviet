package com.liviet.hoo.liviet.model.liviet

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.liviet.hoo.liviet.BuildConfig
import com.liviet.hoo.liviet.model.SharedPreferenceHelper
import com.liviet.hoo.liviet.model.nutrition.FoodRepository
import com.liviet.hoo.liviet.utils.FIREBASE_DB_URL
import com.liviet.hoo.liviet.utils.FIREBASE_VERSION_DB
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@Suppress("unused")
class VersionsRepository @Inject constructor(private val versionsDao: VersionsDao,
                                             private val foodRepository: FoodRepository,
                                             private val firebaseStore: FirebaseFirestore,
                                             private val sharedPreferenceHelper: SharedPreferenceHelper){


    val mCompositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    init {
    }

    private fun loadVersionFromServer(): Observable<Versions> {
        return Observable.create { emitter ->
            firebaseStore.collection(FIREBASE_VERSION_DB).document(FIREBASE_VERSION_DB).get().addOnCompleteListener { res ->
                if (res.isSuccessful) {
                    emitter.onNext(Versions(foodDBVersion = res.result!!.data!!["foodDBVersion"].toString().toLong(),
                            appVersion = res.result!!.data!!["appVersion"].toString().toLong()))
                }else{
                    emitter.onError(Exception())
                }
            }.addOnFailureListener {err ->
                Log.d("error", err.toString())
            }
        }
    }

    private fun loadLocalVersions(): Versions {
        var versionList = versionsDao.get()
        if(versionList.isEmpty()){
            versionsDao.insert(Versions(0,-1, BuildConfig.VERSION_CODE.toLong()))
            // init application
            versionList = versionsDao.get()
        }

        return versionList.first()
    }

    fun saveVersion(versions: Versions): Observable<Versions> {
        versionsDao.insert(versions)
        return Observable.just(versions)
    }

    @SuppressLint("CheckResult")
    fun checkVersion(){
        loadVersionFromServer()
                .subscribe({serverVersion ->
                    val localVersion = loadLocalVersions()
                    if(serverVersion.foodDBVersion != localVersion.foodDBVersion){
                        foodRepository.loadFoodsFromStore().subscribe({
                        },{

                        })
                    }
                    if(serverVersion.appVersion != localVersion.appVersion){
                        // update application from store
                    }

                    // update version db
                    versionsDao.deleteAll()
                    versionsDao.insert(serverVersion)
                }, {
                })
    }
}