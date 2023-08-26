package com.itproger.homework_2.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Dao
import com.itproger.homework_2.activity.model.Note

@Dao
interface Dao {
    @Query("SELECT * FROM Note")
    fun getAll(): List<Note>

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM Note WHERE checkBox = 0")
    fun getNotesFalse(): List<Note>

    @Query("SELECT * FROM Note WHERE checkBox = 1")
    fun getNotesTrue(): List<Note>
}
