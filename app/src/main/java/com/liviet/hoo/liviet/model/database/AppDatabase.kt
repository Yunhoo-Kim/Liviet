package com.liviet.hoo.liviet.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.liviet.hoo.liviet.model.database.converters.Converters
import com.liviet.hoo.liviet.model.nutrition.Diet
import com.liviet.hoo.liviet.model.nutrition.DietDao
import com.liviet.hoo.liviet.model.nutrition.Food
import com.liviet.hoo.liviet.model.nutrition.FoodDao
import com.liviet.hoo.liviet.model.user.User
import com.liviet.hoo.liviet.model.user.UserDao


@Database(entities = [User::class, Food::class, Diet::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun foodDao(): FoodDao
    abstract fun dietDao(): DietDao
}