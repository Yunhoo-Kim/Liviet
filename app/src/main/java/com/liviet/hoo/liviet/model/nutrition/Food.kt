package com.liviet.hoo.liviet.model.nutrition

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey


@Entity(indices = [Index(value = ["name"], unique = true)])
@Suppress("unused")
data class Food(
        @field: PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val name: String,
        val image_url: String,
        val carbon_hydrate: Float,
        val fat: Float,
        val protein: Float,
        val cal: Float,
        val na: Float,
        val amount: Int,
        val measure: String
)