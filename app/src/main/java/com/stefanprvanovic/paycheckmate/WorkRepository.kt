package com.stefanprvanovic.paycheckmate

import androidx.lifecycle.LiveData
import com.stefanprvanovic.paycheckmate.database.Work
import com.stefanprvanovic.paycheckmate.database.WorkDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkRepository(private val workDao: WorkDao) {
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertWork(work: Work) {
        coroutineScope.launch(Dispatchers.IO) {
            workDao.insert(work)
        }
    }

    fun getWork(id: Int): Work {
        var work: Work = Work()

        coroutineScope.launch(Dispatchers.IO) {
            work = workDao.getNote(id)
        }

        return work
    }

    val allWork: LiveData<List<Work>> = workDao.getAllWork()

    fun updateWork(work: Work) {
        coroutineScope.launch(Dispatchers.IO) {
            workDao.update(work)
        }
    }

    fun deleteWork(work: Work) {
        coroutineScope.launch(Dispatchers.IO) {
            workDao.delete(work)
        }
    }
}