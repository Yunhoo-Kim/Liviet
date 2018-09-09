package com.liviet.hoo.liviet.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.liviet.hoo.liviet.model.user.User
import com.liviet.hoo.liviet.model.user.UserDao


@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}