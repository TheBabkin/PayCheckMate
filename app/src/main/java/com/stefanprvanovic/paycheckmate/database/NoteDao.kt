package com.stefanprvanovic.paycheckmate.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Query("SELECT * FROM note WHERE id LIKE :id")
    fun getNote(id: Int): Note

    @Query("SELECT * FROM note")
    fun getAllNotes(): LiveData<List<Note>>

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)
}