package com.liviet.hoo.liviet.viewmodel.user

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.liviet.hoo.liviet.base.BaseViewModel
import com.liviet.hoo.liviet.model.user.User
import com.liviet.hoo.liviet.model.user.UserRepository
import javax.inject.Inject


class UserSetUpViewModel @Inject constructor(private val userRepository: UserRepository): BaseViewModel() {

    val age: MutableLiveData<Int> = MutableLiveData()
    val height: MutableLiveData<Float> = MutableLiveData()
    val weight: MutableLiveData<Float> = MutableLiveData()
    val life_type: MutableLiveData<Int> = MutableLiveData()
    val diet_type: MutableLiveData<Int> = MutableLiveData()
    var heightEntries: MutableLiveData<List<Int>> = MutableLiveData()


    init {

        var heightList = mutableListOf<Int>().apply {
            for (i in 140..220)
                this.add(i)
        }

        heightEntries.value = heightList

    }

    fun insert(user:User){
       userRepository.saveUserInfo(user)
    }

    fun getUserInfo(): User{
        return userRepository.getUser().blockingFirst()
    }

}