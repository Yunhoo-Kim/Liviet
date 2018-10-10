package com.liviet.hoo.liviet.model.database.converters

import android.arch.persistence.room.TypeConverter
import java.util.*


@Suppress("unused")
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = Date(value!!)

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time
}