package com.liviet.hoo.liviet.model.user

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
data class User(
        @field: PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val age: Int,
        val sex: Int,
        val height: Int,
        val weight: Int,
        val life_type: Double,
        val diet_type: Int
)