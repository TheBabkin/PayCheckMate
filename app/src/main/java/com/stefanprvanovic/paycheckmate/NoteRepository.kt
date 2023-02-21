package com.stefanprvanovic.paycheckmate

import androidx.lifecycle.LiveData
import com.stefanprvanovic.paycheckmate.database.Note
import com.stefanprvanovic.paycheckmate.database.NoteDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteRepository(private val noteDao: NoteDao) {
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertNote(note: Note) {
        coroutineScope.launch(Dispatchers.IO) {
            noteDao.insert(note)
        }
    }

    fun getNote(id: Int): Note {
        var note: Note = Note()

        coroutineScope.launch(Dispatchers.IO) {
            note = noteDao.getNote(id)
        }

        return note
    }

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    fun updateNote(note: Note) {
        coroutineScope.launch(Dispatchers.IO) {
            noteDao.update(note)
        }
    }

    fun deleteNote(note: Note) {
        coroutineScope.launch(Dispatchers.IO) {
            noteDao.delete(note)
        }
    }
}