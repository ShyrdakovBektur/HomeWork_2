package com.itproger.homework_2.database

import androidx.room.RoomDatabase
import androidx.room.Database
import com.itproger.homework_2.activity.model.Note


@Database(entities = [Note::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun dao(): Dao
}