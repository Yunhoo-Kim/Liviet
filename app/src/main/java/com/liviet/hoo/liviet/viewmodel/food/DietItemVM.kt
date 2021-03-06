package com.liviet.hoo.liviet.viewmodel.food

import android.arch.lifecycle.MutableLiveData
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseViewModel
import com.liviet.hoo.liviet.model.nutrition.Diet
import com.liviet.hoo.liviet.model.nutrition.DietRepository
import com.liviet.hoo.liviet.model.nutrition.Food
import com.liviet.hoo.liviet.model.nutrition.FoodRepository
import com.liviet.hoo.liviet.ui.food.AddFoodListAdapter
import com.liviet.hoo.liviet.ui.food.FoodListAdapter
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject


@Suppress("unused")
class DietItemVM: BaseViewModel() {

    var name: MutableLiveData<String> = MutableLiveData()
    val amount: MutableLiveData<String> = MutableLiveData()
    val image: MutableLiveData<String> = MutableLiveData()
    val amt: MutableLiveData<String> = MutableLiveData()
    val resId: MutableLiveData<Int> = MutableLiveData()
    val measure: MutableLiveData<String> = MutableLiveData()
    var amountText: MutableLiveData<String> = MutableLiveData()
    var carbon: MutableLiveData<String> = MutableLiveData()
    var protein: MutableLiveData<String> = MutableLiveData()
    var fat: MutableLiveData<String> = MutableLiveData()
    var kcal: MutableLiveData<String> = MutableLiveData()

    fun bind(diet: Diet, food: Food){
        this.name.value = food.name
        this.amount.value = diet.amount.toString()
        this.amountText.value = "${food.amount} ${food.measure}"
        this.image.value = food.imageUrl
        this.amt.value = "${diet.amount}${food.measure}"
        this.resId.value = 0
        this.measure.value = food.measure
        this.carbon.value = String.format("%.2fg", food.carbonHydrate / food.amount.toFloat() * diet.amount)
        this.protein.value = String.format("%.2fg", food.protein / food.amount.toFloat() * diet.amount)
        this.fat.value = String.format("%.2fg", food.fat / food.amount.toFloat() * diet.amount)
        this.kcal.value = "${((food.cal.toFloat() / food.amount.toFloat()) * diet.amount).toInt()} Kcal"
    }
}