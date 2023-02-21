package com.stefanprvanovic.paycheckmate

import androidx.lifecycle.LiveData
import com.stefanprvanovic.paycheckmate.database.Work
import com.stefanprvanovic.paycheckmate.database.WorkDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkRepository(private val workDao: WorkDao) {
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertNote(work: Work) {
        coroutineScope.launch(Dispatchers.IO) {
            workDao.insert(work)
        }
    }

    fun getNote(id: Int): Work {
        var work: Work = Work()

        coroutineScope.launch(Dispatchers.IO) {
            work = workDao.getNote(id)
        }

        return work
    }

    val allNotes: LiveData<List<Work>> = workDao.getAllWork()

    fun updateNote(work: Work) {
        coroutineScope.launch(Dispatchers.IO) {
            workDao.update(work)
        }
    }

    fun deleteNote(work: Work) {
        coroutineScope.launch(Dispatchers.IO) {
            workDao.delete(work)
        }
    }
}