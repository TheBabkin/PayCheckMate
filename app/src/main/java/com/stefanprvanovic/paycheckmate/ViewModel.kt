package com.stefanprvanovic.paycheckmate

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stefanprvanovic.paycheckmate.database.Work
import com.stefanprvanovic.paycheckmate.database.WorkDatabase

class ViewModel(application: Application) : ViewModel() {

    val allWork: LiveData<List<Work>>
    private val repository: WorkRepository

    init {
        val workDb = WorkDatabase.getInstance(application)
        val workDao = workDb.workDao()

        repository = WorkRepository(workDao)

        allWork = repository.allWork
    }

    fun insertWork(work: Work) {
        repository.insertWork(work)
    }

    fun getWork(id: Int) {
        repository.getWork(id)
    }

    fun updateWork(work: Work) {
        repository.updateWork(work)
    }

    fun deleteWork(work: Work) {
        repository.deleteWork(work)
    }
}