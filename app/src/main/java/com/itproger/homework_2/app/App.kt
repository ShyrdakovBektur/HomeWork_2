package com.itproger.homework_2.app

import android.app.Application
import androidx.room.Room
import com.itproger.homework_2.database.Database

class App: Application() {

    override fun onCreate() {
        super.onCreate()
    database = Room.databaseBuilder(applicationContext, Database::class.java,"NoteFile")
        .allowMainThreadQueries().build()
    }

    companion object{
        lateinit var database: Database
    }
}