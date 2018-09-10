package com.liviet.hoo.liviet.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.Spinner
import android.widget.SpinnerAdapter


@BindingAdapter("adapter")
@Suppress("unused")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("entries")
@Suppress("unused")
fun Spinner.setEntries(entries: List<Any>?) {
    setEntries(entries)
}
