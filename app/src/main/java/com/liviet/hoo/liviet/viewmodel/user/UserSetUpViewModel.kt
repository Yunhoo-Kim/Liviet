package com.liviet.hoo.liviet.viewmodel.user

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.util.Log
import android.widget.ArrayAdapter
import com.liviet.hoo.liviet.base.BaseViewModel
import com.liviet.hoo.liviet.model.user.User
import com.liviet.hoo.liviet.model.user.UserRepository
import javax.inject.Inject


class UserSetUpViewModel @Inject constructor(private val userRepository: UserRepository): BaseViewModel() {

    val age: MutableLiveData<String> = MutableLiveData()
    val sex: MutableLiveData<Boolean> = MutableLiveData()
    val height: MutableLiveData<String> = MutableLiveData()
    val weight: MutableLiveData<String> = MutableLiveData()
    val life_type: MutableLiveData<Int> = MutableLiveData()
    val diet_type: MutableLiveData<Int> = MutableLiveData()

    var ageDfIdx:Int = 10
    var heightDfIdx:Int = 30
    var weightDfIdx:Int = 30

    var ageEntries: MutableList<String> = mutableListOf<String>().apply {
        for(i in 14..100)
            this.add("${i}세")
    }
    var heightEntries: MutableList<String> = mutableListOf<String>().apply {
        for (i in 140..220)
            this.add("$i cm")
    }
    var weightEntries: MutableList<String> = mutableListOf<String>().apply {
        for (i in 40..200)
            this.add("$i kg")
    }


    init {

    }

    fun insert(user:User){
        userRepository.saveUserInfo(user)
    }

    fun getUserInfo(): User{
        return userRepository.getUser().blockingFirst()
    }

    fun printUserInfo(){
        Log.d("PrintUserInfo", "Age : ${age.value}")
        Log.d("PrintUserInfo", "Weight : ${weight.value}")
        Log.d("PrintUserInfo", "Height : ${height.value}")
        Log.d("PrintUserInfo", "Is Main: ${sex.value}")
    }

}