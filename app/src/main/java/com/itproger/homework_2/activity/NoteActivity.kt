package com.itproger.homework_2.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.itproger.homework_2.activity.model.Note
import com.itproger.homework_2.activity.viewmodel.ViewModel
import com.itproger.homework_2.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding
    private lateinit var viewModel: ViewModel
    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        note = intent.getSerializableExtra(MainActivity.UPDATE_NOTE_KEY) as Note?
        if (note == null) {
            createNote()
        } else {
            initView()
        }
    }

    private fun createNote() {
        binding.btnSave.setOnClickListener {
            val data = Note(
                title = binding.etTitle.text.toString(),
                desc = binding.etDescription.text.toString(),
            )
            viewModel.addNote(data)
            finish()
        }
    }

             @SuppressLint("SetTextI18n")
             private fun initView() {
                 with(binding) {
                     etTitle.setText(note?.title)
                     etDescription.setText(note?.desc)
                     btnSave.text = "Update"
                     btnSave.setOnClickListener {
                         val date = note!!.copy(
                             title = binding.etTitle.text.toString(),
                             desc = binding.etDescription.text.toString(),
                         )
                         viewModel.updateNote(date)
                         finish()
                     }
                 }
             }
}