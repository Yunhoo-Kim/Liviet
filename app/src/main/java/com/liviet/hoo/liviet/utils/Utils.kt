package com.liviet.hoo.liviet.utils

import android.graphics.Bitmap
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.util.*


@Suppress("unused")
class Utils {
    companion object {
        fun makeCalendarToDate(calendar: Calendar): Date{
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            return calendar.time
        }

        fun uploadImageToGCS(bitmap: Bitmap, storageReference: StorageReference): UploadTask{
            val fileName = "abc.png"
//            val storageMetadata = StorageMetadata.Builder()
//                    .setContent

            val imageRef = storageReference.child(fileName)
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

            return imageRef.putBytes(baos.toByteArray())

        }
    }
}