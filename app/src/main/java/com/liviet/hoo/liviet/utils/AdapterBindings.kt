package com.liviet.hoo.liviet.utils

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
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

@BindingAdapter("imageRes")
@Suppress("unused")
fun setImageResource(view: ImageView, image_id: MutableLiveData<Int>?){
    if(image_id!!.value == 0)
        return

    Log.d("Image Tag", image_id!!.value.toString())
    view.setImageResource(image_id.value!!)
}

@BindingAdapter("imageResUri")
@Suppress("unused")
fun setImageResourceByURI(view: ImageView, image_id: MutableLiveData<String>?){
    Log.d("Image String", image_id!!.value)
    if(image_id.value.isNullOrEmpty())
        return

    Glide.with(view.getParentActivity()!!)
            .load(FirebaseStorage.getInstance().reference.child(image_id.value!!))
            .into(view)
//    view.setImageURI(Uri.parse(image_id.value))
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
