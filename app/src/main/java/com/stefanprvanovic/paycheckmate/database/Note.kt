package com.stefanprvanovic.paycheckmate.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val customerName: String = "",
    val noteDescription: String = "",
    val customerAddress: String = "",
    val dateTime: String = "",
    val price: Int = 0,
    val payed: Boolean = false
)