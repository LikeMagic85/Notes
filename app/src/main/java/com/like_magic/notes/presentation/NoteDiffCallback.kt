package com.like_magic.notes.presentation

import androidx.recyclerview.widget.DiffUtil
import com.like_magic.notes.domen.entity.NoteEntity

class NoteDiffCallback(): DiffUtil.ItemCallback<NoteEntity>() {
    override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
        return oldItem == newItem
    }

}
