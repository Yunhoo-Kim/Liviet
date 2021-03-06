package com.liviet.hoo.liviet.model.nutrition

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.liviet.hoo.liviet.utils.LUNCH
import java.util.*


//@Entity(foreignKeys = [ForeignKey(entity = Food::class,
//        parentColumns = ["id"],
//        childColumns = ["foodId"],
//        onDelete = ForeignKey.CASCADE)])
@Entity
@Suppress("unused")
data class Diet(
        @field: PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        var date: Date,
        val type: Int = LUNCH,
        val foodId: Long,
        val amount: Int
)