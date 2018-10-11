package com.liviet.hoo.liviet.model.nutrition

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import java.util.*


@Entity(foreignKeys = [ForeignKey(entity = Food::class,
        parentColumns = ["id"],
        childColumns = ["foodId"],
        onDelete = ForeignKey.CASCADE)])
@Suppress("unused")
data class Diet(
        @field: PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        var date: Date,
        val foodId: Long,
        val amount: Int
)