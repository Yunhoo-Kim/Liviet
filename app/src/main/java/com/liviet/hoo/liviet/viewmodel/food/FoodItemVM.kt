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
class FoodItemVM: BaseViewModel() {

    var name: MutableLiveData<String> = MutableLiveData()
    val amount: MutableLiveData<String> = MutableLiveData()
    val image: MutableLiveData<String> = MutableLiveData()

    fun bind(food: Food){
        this.name.value = food.name
        this.amount.value = food.amount.toString()
        this.image.value = food.image_url
    }
}