package com.itproger.homework_2.activity.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.itproger.homework_2.activity.model.Note
import com.itproger.homework_2.databinding.ItemNoteBinding

class NoteAdapter (
    private val onLongClickListenerNote: (Note) -> Unit,
    private val isNoteChecked: (Note, Boolean) -> Unit,
    private val onClickNote: (Note) -> Unit
) : Adapter<NoteAdapter.NoteViewHolder>() {
    private var list = mutableListOf<Note>()


    @SuppressLint("NotifyDataSetChanged")
    fun setNote(list: MutableList<Note>) {
        this.list = list
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteNote(note: Note) {
        list.remove(note)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(note: Note) = with(binding) {
         title.text = note.title
            description.text = note.desc
            checkBox.isChecked = note.checkBox
            itemView.setOnClickListener {
                onLongClickListenerNote(note)
                true
            }
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                isNoteChecked(note, isChecked)
                Log.e("ololo", "onBind: $note \t $isChecked")
            }
            itemView.setOnClickListener {
                onClickNote(note)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )


        override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
            holder.onBind(list[position])
        }

        override fun getItemCount() = list.size
    }
