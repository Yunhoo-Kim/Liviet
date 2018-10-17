package com.liviet.hoo.liviet.model.nutrition

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
@Suppress("unused")
interface FoodDao {
    @Query("SELECT * FROM food")
    fun getFoods(): List<Food>

    @Query("SELECT * FROM food where id=:id")
    fun getFoodById(id: Long): Food

    @Query("SELECT * FROM food where name LIKE %:name%")
    fun searchFoodByName(name: String): List<Food>

    @Insert(onConflict = REPLACE)
    fun insert(food: Food): Long

    @Query("DELETE FROM food")
    fun deleteAll()
}
