package com.liviet.hoo.liviet.model.user

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
data class User(
        @field: PrimaryKey(autoGenerate = true)
        val id: Long,
        val age: Int,
        val sex: Int,
        val height: Float,
        val weight: Float,
        val life_type: Int,
        val diet_type: Int
)