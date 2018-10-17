package com.liviet.hoo.liviet.model.nutrition

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey


@Entity(indices = [Index(value = ["name"], unique = true)])
@Suppress("unused")
data class Food(
        @field: PrimaryKey
        val id: Long = 0,
        val name: String,
        val imageUrl: String,
        val carbonHydrate: Double,
        val fat: Double,
        val protein: Double,
        val cal: Int,
        val na: Double = 0.0,
        val amount: Int,
        val measure: String,
        var selected: Boolean = false
)