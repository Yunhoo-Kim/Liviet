package com.liviet.hoo.liviet.viewmodel.user

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.base.BaseViewModel
import com.liviet.hoo.liviet.model.nutrition.NutritionResult
import com.liviet.hoo.liviet.model.user.User
import com.liviet.hoo.liviet.model.user.UserRepository
import com.liviet.hoo.liviet.ui.user.BodyInfoCardListAdapter
import com.liviet.hoo.liviet.ui.user.NutritionListAdapter
import com.liviet.hoo.liviet.utils.UiUtli
import com.liviet.hoo.liviet.utils.Utils
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

    val bodyInfoListAdapter: BodyInfoCardListAdapter by lazy {
        BodyInfoCardListAdapter()
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
        updateUserInfo()
    }

    fun updateUserInfo(){
        try{
            val user = getUserInfo()
            this.age.value = "${user.age}세"
            this.height.value = "${user.height} cm"
            this.weight.value = "${user.weight} kg"
            this.sex.value = user.sex == 0
            this.ageDfIdx = this.ageEntries.indexOf(this.age.value!!)
            this.heightDfIdx = this.heightEntries.indexOf(this.height.value!!)
            this.weightDfIdx = this.weightEntries.indexOf(this.weight.value!!)

            Log.d("Error", "${this.age.value} ${this.weight.value} ${this.height.value}")
        }catch (e: NullPointerException){
            Log.d("Error", "Error NullPointer")
        }
    }

    fun saveUserInfo(){
        val age:Int = this.age.value!!.replace("""[\D]+""".toRegex(), "").toInt()
        val weight:Int = this.weight.value!!.replace("""[\D]+""".toRegex(), "").toInt()
        val height:Int = this.height.value!!.replace("""[\D]+""".toRegex(), "").toInt()
        val sex:Int = if(this.sex.value!!) 0 else 1
        val lifeType = this.life_type.value!!

        val user = User(age = age, weight = weight,
                height = height,
                sex = sex,
                life_type = lifeType,
                diet_type = 0)
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

        val basalMetabolism:Double = Utils.getBasalMetabolism(weight, age, height, sex)
        val standardWeight:Double = if(height > 150) (height - 100) * 0.9 else (height - 100).toDouble()
        val bmi = (weight / ((height * height) * 0.0001)).toInt()
        val bmiDegree = "정상체중"

        val kcal: Int = (basalMetabolism * life_type.value!!).toInt()
        this.kcal.value = "$kcal kcal"

        val carbohydrate = Utils.getCarbonHydrate(weight, age, height, sex, life_type.value!!)
        val fat = Utils.getFat(weight, age, height, sex, life_type.value!!)
        val protein = Utils.getProtein(weight, age, height, sex, life_type.value!!)

        val nList = mutableListOf<NutritionResult>()
        val bList = mutableListOf<NutritionResult>()

        nList.add(NutritionResult(name = R.string.protein, ratio = "0", amt = "${protein}g"))
        nList.add(NutritionResult(name = R.string.carbohydrate, ratio = "0", amt = "${carbohydrate}g"))
        nList.add(NutritionResult(name = R.string.fat, ratio = "0", amt = "${fat}g"))
        bList.add(NutritionResult(name = R.string.bmi, ratio = "1", amt = "$bmi $bmiDegree"))
        bList.add(NutritionResult(name = R.string.standard_weight, ratio = "1", amt = "${standardWeight.toInt()}kg"))

        nutritionListAdapter.updateNutritionResultList(nList)
        bodyInfoListAdapter.updateBodyResultList(bList)
    }

    fun printUserInfo(){
        Log.d("PrintUserInfo", "Age : ${age.value}")
        Log.d("PrintUserInfo", "Weight : ${weight.value}")
        Log.d("PrintUserInfo", "Height : ${height.value}")
        Log.d("PrintUserInfo", "Is Main: ${sex.value}")
    }

}