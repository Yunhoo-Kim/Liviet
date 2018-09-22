package com.liviet.hoo.liviet.utils

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.TextView
import com.liviet.hoo.liviet.utils.extension.getParentActivity


@BindingAdapter("adapter")
@Suppress("unused")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("android:text")
@Suppress("unused")
fun setText(view: TextView, data: MutableLiveData<Float>?){
    Log.d("AndroidText", data!!.value.toString())
    if (data != null)
        view.text = data.value.toString()
}

//@BindingAdapter("android:drawable")
//@Suppress("unused")
//fun setImage(view: ImageView, image_url: MutableLiveData<String>){
//    Glide
//}


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
