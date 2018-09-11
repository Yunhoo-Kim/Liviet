package com.liviet.hoo.liviet.viewmodel.user

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.util.Log
import android.widget.ArrayAdapter
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseViewModel
import com.liviet.hoo.liviet.model.nutrition.NutritionResult
import com.liviet.hoo.liviet.model.user.User
import com.liviet.hoo.liviet.model.user.UserRepository
import com.liviet.hoo.liviet.ui.user.NutritionListAdapter
import com.liviet.hoo.liviet.utils.UiUtli
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject


class UserSetUpViewModel @Inject constructor(private val userRepository: UserRepository): BaseViewModel() {

    val age: MutableLiveData<String> = MutableLiveData()
    val sex: MutableLiveData<Boolean> = MutableLiveData()
    val height: MutableLiveData<String> = MutableLiveData()
    val weight: MutableLiveData<String> = MutableLiveData()
    val life_type: MutableLiveData<Double> = MutableLiveData()
    val diet_type: MutableLiveData<Int> = MutableLiveData()
    val kcal: MutableLiveData<String> = MutableLiveData()

    val nutritionListAdapter: NutritionListAdapter by lazy {
        NutritionListAdapter()
    }

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

    fun calcUserNutritionResult(){

        val age:Int = this.age.value!!.replace("""[\D]+""".toRegex(), "").toInt()
        val weight:Int = this.weight.value!!.replace("""[\D]+""".toRegex(), "").toInt()
        val height:Int = this.height.value!!.replace("""[\D]+""".toRegex(), "").toInt()
        val sex:Int = if(this.sex.value!!) 0 else 1

        val basalMetabolism:Double = (12.2 * weight) - (4.82 * age) - (126.1 * sex) + (2.85 * height) + 468.3
        val standardWeight:Double = if(height > 150) (height - 100) * 0.9 else (height - 100).toDouble()
        val bmi:Int = weight / (height * height)

        val kcal: Int = (basalMetabolism * life_type.value!!).toInt()
        this.kcal.value = "${kcal} kcal"

        val carbohydrate = UiUtli.getFormatNumber(((kcal * 0.65) / 4).toInt())
        val fat = UiUtli.getFormatNumber(((kcal * 0.15) / 9).toInt())
        val protein = UiUtli.getFormatNumber(((kcal * 0.20) / 4).toInt())

        val list = mutableListOf<NutritionResult>()
        list.add(NutritionResult(name = R.string.protein, ratio = "20", amt = "${protein}g"))
        list.add(NutritionResult(name = R.string.carbohydrate, ratio = "65", amt = "${carbohydrate}g"))
        list.add(NutritionResult(name = R.string.fat, ratio = "15", amt = "${fat}g"))
        list.add(NutritionResult(name = R.string.bmi, ratio = "15", amt = "${bmi}g"))
        list.add(NutritionResult(name = R.string.standard_weight, ratio = "15", amt = "${standardWeight}kg"))
        nutritionListAdapter.updateNutritionResultList(list)
    }

    fun printUserInfo(){
        Log.d("PrintUserInfo", "Age : ${age.value}")
        Log.d("PrintUserInfo", "Weight : ${weight.value}")
        Log.d("PrintUserInfo", "Height : ${height.value}")
        Log.d("PrintUserInfo", "Is Main: ${sex.value}")
    }

}