package com.liviet.hoo.liviet.utils

import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import java.io.ByteArrayOutputStream
import java.net.URI
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

        fun convertBMURI(contentResolver: ContentResolver, bitmap: Bitmap): Uri{
            val bytes = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, UUID.randomUUID().toString(), null)
            return Uri.parse(path)
        }
    }
}