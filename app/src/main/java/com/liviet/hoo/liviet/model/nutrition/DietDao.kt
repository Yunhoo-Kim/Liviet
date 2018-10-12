package com.liviet.hoo.liviet.model.nutrition

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import java.util.*

@Dao
@Suppress("unused")
interface DietDao {
    @Query("SELECT * FROM diet")
    fun getDiets(): List<Diet>

    @Query("SELECT * FROM diet where date=:date")
    fun getDietByDate(date: Date): List<Diet>

    @Query("SELECT * FROM diet where id=:id")
    fun getDietById(id: Long): Diet

    @Insert(onConflict = REPLACE)
    fun insert(diet: Diet): Long

    @Query("DELETE  FROM diet where id=:id")
    fun delDiet(id: Long)

    @Query("DELETE FROM diet")
    fun deleteAll()
}
