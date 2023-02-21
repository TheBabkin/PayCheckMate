package com.stefanprvanovic.paycheckmate.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Work(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val customerName: String = "",
    val workDescription: String = "",
    val customerAddress: String = "",
    val dateTime: String = "",
    val price: Int = 0,
    val payed: Boolean = false
)