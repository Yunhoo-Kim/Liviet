package com.liviet.hoo.liviet.model.user

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query


@Dao
interface UserDao {
    @Query("SELECT * FROM user where id = :id")
    fun getUserById(id: Long): User

    @Query("SELECT * FROM user LIMIT 1")
    fun getUser():User

    @Insert(onConflict = REPLACE)
    fun insert(user: User): Long
}