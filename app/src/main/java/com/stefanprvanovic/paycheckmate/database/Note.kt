package com.stefanprvanovic.paycheckmate.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
class Note {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int = 0

    val noteTitle: String = ""
    val customerName: String = ""
    val noteDescription: String = ""
    val dateTime: String = ""
    val price: Double = 0.0
    val payed: Boolean = false
}