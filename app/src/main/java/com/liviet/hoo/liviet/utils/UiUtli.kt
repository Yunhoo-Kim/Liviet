package com.liviet.hoo.liviet.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import java.text.NumberFormat
import java.util.*


class UiUtli {
    companion object {
        fun addNewFragment(activity:FragmentActivity, fragment: Fragment, container_id:Int){
            activity.supportFragmentManager.apply {
                this.beginTransaction()
                        .addToBackStack(null)
                        .add(container_id, fragment, "")
                        .commit()
            }
        }

        fun replaceNewFragment(activity:FragmentActivity, fragment: Fragment, container_id:Int){
            activity.supportFragmentManager.apply {
                this.beginTransaction()
                        .addToBackStack(null)
                        .replace(container_id, fragment, "")
                        .commit()
            }
        }

        fun getFormatNumber(number: Int): String = NumberFormat.getNumberInstance(Locale.US).format(number)
    }
}