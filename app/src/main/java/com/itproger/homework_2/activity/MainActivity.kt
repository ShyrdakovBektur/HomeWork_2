package com.itproger.homework_2.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.itproger.homework_2.activity.model.Note
import com.itproger.homework_2.activity.adapter.NoteAdapter
import com.itproger.homework_2.activity.viewmodel.ViewModel
import com.itproger.homework_2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModel
    private val adapter = NoteAdapter(
        this::onLongClickNote,
        this::isCheckedNote,
        this::onClickNote,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        binding.recyclerView.adapter = adapter
        initView()
        initClick()
        initSpinner()
    }

    private fun initClick() {
        binding.btnNote.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }
    }
    private fun initView() {
        viewModel.list.observe(this) { updateList ->
            binding.recyclerView.adapter = adapter
            adapter.setNote(updateList)
        }
    }
    private fun onLongClickNote(note: Note) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Do you want to delete note?")
            .setMessage("It will be impossible to restore the note!")
            .setPositiveButton("Ok") {dialog: DialogInterface, _: Int ->
                viewModel.deleteNote(note)
                adapter.deleteNote(note)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") {dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
        dialogBuilder.show()
    }
    private fun onClickNote(note: Note) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra(UPDATE_NOTE_KEY, note)
        startActivity(intent)
    }
    private fun isCheckedNote(note: Note, isChecked: Boolean = false) {
        viewModel.checkedNote(note, isChecked)
    }
    private fun initSpinner() {
  val noteFilterList = arrayOf(
      "All notes",
      "Uncompleted",
      "Completed"
  )
        val adapterSpinner = ArrayAdapter(this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, noteFilterList)
        adapterSpinner.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        binding.spinner.adapter = adapterSpinner
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (noteFilterList[position]) {
                    "All notes" -> viewModel.getNote()
                    "Uncompleted" -> viewModel.filtrateFalseNotes()
                    "completed" -> viewModel.filtrateTrueNotes()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.getNote()
            }
        }
    }
    companion object {
        const val UPDATE_NOTE_KEY = "update_task_key"
    }

    override fun onResume() {
        super.onResume()
        initSpinner()
    }

}