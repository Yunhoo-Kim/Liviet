package com.liviet.hoo.liviet.viewmodel.user

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.util.Log
import android.widget.ArrayAdapter
import com.liviet.hoo.liviet.base.BaseViewModel
import com.liviet.hoo.liviet.model.nutrition.NutritionResult
import com.liviet.hoo.liviet.model.user.User
import com.liviet.hoo.liviet.model.user.UserRepository
import javax.inject.Inject


class NutriItemVM: BaseViewModel() {

    var name: MutableLiveData<Int> = MutableLiveData()
    val amt: MutableLiveData<String> = MutableLiveData()
    val ratio: MutableLiveData<String> = MutableLiveData()

    fun bind(nutritionResult: NutritionResult){
//        this.name.value = nutritionResult.name
        Log.d("Result", "${nutritionResult.amt}, ${nutritionResult.ratio}, ${nutritionResult.name}")
        this.name.value = nutritionResult.name
        this.amt.value = nutritionResult.amt
        this.ratio.value = nutritionResult.ratio
    }
}