package com.liviet.hoo.liviet.utils

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.net.Uri
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.RadarData
import com.google.firebase.storage.FirebaseStorage
import com.liviet.hoo.liviet.R
import com.liviet.hoo.liviet.utils.extension.getParentActivity


@BindingAdapter("adapter")
@Suppress("unused")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("android:text")
@Suppress("unused")
fun setText(view: TextView, data: MutableLiveData<Float>?){
    if (data != null)
        view.text = data.value.toString()
}

@BindingAdapter("imageRes")
@Suppress("unused")
fun setImageResource(view: ImageView, image_id: MutableLiveData<Int>?){
    if(image_id!!.value == 0){
        view.setImageResource(R.drawable.dish)
        return
    }
    view.setImageResource(image_id.value!!)
}

@BindingAdapter("imageResUri")
@Suppress("unused")
fun setImageResourceByURI(view: ImageView, image_id: MutableLiveData<String>?){
    if(image_id!!.value.isNullOrEmpty()) {
        view.setImageResource(R.drawable.dish)
        return
    }

    Glide.with(view.getParentActivity()!!)
            .load(FirebaseStorage.getInstance().reference.child(image_id.value!!))
//            .apply(RequestOptions.)
            .into(view)
}

@Suppress("unused")
@BindingAdapter("tabadapter")
fun setTabAdapter(view: ViewPager, adapter: FragmentPagerAdapter) {
    view.adapter = adapter
}

//@Suppress("unused")
//@BindingAdapter("chartData")
//fun setChartData(view: RadarChart, data: MutableLiveData<RadarData>) {
//    Log.d("Nutrition", "Data In")
//    view.data = data.value
//    view.invalidate()
//}

@Suppress("unused")
@BindingAdapter("chartData")
fun setChartData(view: HorizontalBarChart, data: MutableLiveData<BarData>) {
    view.data = data.value
    view.invalidate()
}

@Suppress("unused")
@BindingAdapter("weeklyData")
fun setWeeklyData(view: LineChart, data: MutableLiveData<LineData>) {
    view.data = data.value
    view.invalidate()
}

//@BindingAdapter("entries")
//@Suppress("unused")
//fun Spinner.setEntries(entries: List<Any>?) {
//    setEntries(entries)
//}
//
//@BindingAdapter("selection")
//@Suppress("unused")
//fun Spinner.setSelection(position:Int){
//    setSelection(position)
//}
