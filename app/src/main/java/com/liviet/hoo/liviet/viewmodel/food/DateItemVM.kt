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
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@Suppress("unused")
class DateItemVM: BaseViewModel() {

    private val dayFormat: String = "d"
//    private val daySFormat = SimpleDateFormat(dayFormat, Locale.getDefault()).apply{
//        this.timeZone = TimeZone.getTimeZone("KST")}
    private val daySFormat = SimpleDateFormat(dayFormat, Locale.getDefault())

    private val dayOWFormat: String = "E"
//    private val daySOWFormat = SimpleDateFormat(dayOWFormat, Locale.getDefault()).apply{
//        this.timeZone = TimeZone.getTimeZone("KST")}
    private val daySOWFormat = SimpleDateFormat(dayOWFormat, Locale.getDefault())

    var day: MutableLiveData<String> = MutableLiveData()
    var dayOfWeek: MutableLiveData<String> = MutableLiveData()

    fun bind(date : Date){
        this.day.value = daySFormat.format(date)
        this.dayOfWeek.value = daySOWFormat.format(date)
    }
}