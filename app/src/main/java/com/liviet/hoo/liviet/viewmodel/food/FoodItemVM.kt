package com.liviet.hoo.liviet.viewmodel.food

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.ObservableField
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
    val amt: MutableLiveData<String> = MutableLiveData()
    val resId: MutableLiveData<Int> = MutableLiveData()
    val measure: MutableLiveData<String> = MutableLiveData()
    var amountText: MutableLiveData<String> = MutableLiveData()
    var carbon: MutableLiveData<String> = MutableLiveData()
    var cal: MutableLiveData<String> = MutableLiveData()
    var protein: MutableLiveData<String> = MutableLiveData()
    var fat: MutableLiveData<String> = MutableLiveData()

    fun bind(food: Food){
        this.name.value = food.name
        this.amount.value = food.amount.toString()
        this.amountText.value = food.amount.toString()
        this.image.value = food.imageUrl
        this.amt.value = "${food.amount}${food.measure}"
        this.resId.value = 0
        this.measure.value = food.measure
        this.carbon.value = "${food.carbonHydrate}g"
        this.protein.value = "${food.protein}g"
        this.fat.value = "${food.fat}g"
        this.cal.value = "${food.cal}"
    }
}