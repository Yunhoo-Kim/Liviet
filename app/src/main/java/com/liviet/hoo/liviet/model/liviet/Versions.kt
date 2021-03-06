package com.liviet.hoo.liviet.model.liviet

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Versions(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val foodDBVersion: Long,
        val appVersion: Long
)
