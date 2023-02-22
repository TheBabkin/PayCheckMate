package com.stefanprvanovic.paycheckmate.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Work(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val customer: String = "",
    val customerAddress: String = "",
    val workDescription: String = "",
    val dateTime: String = "",
    val price: String = "",
    val payed: Boolean = false
)