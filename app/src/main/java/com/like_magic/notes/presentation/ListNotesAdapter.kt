package com.like_magic.notes.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.like_magic.notes.databinding.ItemNotesListBinding
import com.like_magic.notes.domen.entity.NoteEntity

class ListNotesAdapter :
    ListAdapter<NoteEntity, ListNotesAdapter.NoteViewHolder>(NoteDiffCallback()) {


    var onCoinClickListener: ((NoteEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNotesListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        with(holder) {
            binding.noteTitle.text = note.title
            binding.noteTime.text = note.time
            binding.noteLocation.text = note.location
            onCoinClickListener?.invoke(note)
        }
    }

    inner class NoteViewHolder(val binding: ItemNotesListBinding) :
        RecyclerView.ViewHolder(binding.root)

}