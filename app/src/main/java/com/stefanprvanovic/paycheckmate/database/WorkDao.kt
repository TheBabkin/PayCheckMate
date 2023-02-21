package com.stefanprvanovic.paycheckmate.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WorkDao {

    @Insert
    fun insert(work: Work)

    @Query("SELECT * FROM note WHERE id LIKE :id")
    fun getNote(id: Int): Work

    @Query("SELECT * FROM note")
    fun getAllWork(): LiveData<List<Work>>

    @Update
    fun update(work: Work)

    @Delete
    fun delete(work: Work)
}