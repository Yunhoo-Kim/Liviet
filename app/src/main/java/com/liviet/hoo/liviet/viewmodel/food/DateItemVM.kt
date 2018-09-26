package com.liviet.hoo.liviet.viewmodel.food

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.util.Log
import android.widget.ArrayAdapter
import com.liviet.hoo.liviet.base.BaseViewModel
import com.liviet.hoo.liviet.model.nutrition.Food
import com.liviet.hoo.liviet.model.nutrition.NutritionResult
import com.liviet.hoo.liviet.model.user.User
import com.liviet.hoo.liviet.model.user.UserRepository
import javax.inject.Inject


@Suppress("unused")
class DateItemVM: BaseViewModel() {

    var day: MutableLiveData<String> = MutableLiveData()

    fun bind(date: String){
        this.day.value = date
    }
}