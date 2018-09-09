package com.liviet.hoo.liviet.model.user

import com.liviet.hoo.liviet.model.SharedPreferenceHelper
import io.reactivex.Observable
import javax.inject.Inject


class UserRepository @Inject constructor(private val userDao: UserDao, private val sharedPreferenceHelper: SharedPreferenceHelper){
//    fun getUserInfo(): Observable<User> {
//        return Observable.fromCallable(userDao.getUserById())
//    }

    fun saveUserInfo(user: User): Observable<User> {
        val userId: Long = userDao.insert(user)
        val savedUser: User = userDao.getUserById(userId)
        return Observable.just(savedUser)
    }

    fun getUser(): Observable<User> {
        return Observable.just(userDao.getUser())
    }
}