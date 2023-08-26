package com.itproger.homework_2.activity.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itproger.homework_2.activity.model.Note
import com.itproger.homework_2.app.App

class ViewModel : androidx.lifecycle.ViewModel() {
    private val dao = App.database.dao()
    private var note = dao.getAll().toMutableList()
     var list = MutableLiveData(note)
    var lists:LiveData<MutableList<Note>> = list

    fun getNote(){
        note = dao.getAll().toMutableList()
        list.value = note
        Log.e("ololo", "getNote: ${list.value}")
    }
  fun addNote(note: Note) {
      dao.insert(note)
  }
    fun updateNote(note: Note) {
        dao.update(note)
    }

    fun deleteNote(note: Note) {
        dao.delete(note)
    }
    fun checkedNote(note: Note, isChecked: Boolean) {
        val data = note.copy(checkBox = isChecked)
        Log.e("ololo", "checkedNote: $data")
        dao.update(data)
    }
    fun filtrateFalseNotes(){
        note = dao.getNotesFalse().toMutableList()
        list.value = note
        Log.e("ololo", "filtrateFalseNotes: ${list.value}")
    }

    fun filtrateTrueNotes(){
        note = dao.getNotesTrue().toMutableList()
        list.value = note
        Log.e("ololo", "filtrateTrueNotes: ${list.value}")
    }
}