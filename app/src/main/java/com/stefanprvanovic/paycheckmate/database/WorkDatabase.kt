package com.stefanprvanovic.paycheckmate.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(Work::class)], version = 1)
abstract class WorkDatabase : RoomDatabase() {

    abstract fun workDao(): WorkDao

    companion object {
        private var INSTANCE: WorkDatabase? = null

        fun getInstance(context: Context): WorkDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WorkDatabase::class.java,
                        "note_database.db"
                    ).fallbackToDestructiveMigration().build()
                }

                return instance
            }
        }
    }
}