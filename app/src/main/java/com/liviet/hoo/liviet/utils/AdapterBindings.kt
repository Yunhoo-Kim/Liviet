package com.liviet.hoo.liviet.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView


@BindingAdapter("adapter")
@Suppress("unused")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}
