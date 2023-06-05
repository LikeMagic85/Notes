package com.like_magic.notes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_list")
data class NoteDbModel(
    @PrimaryKey
    val id: Int,
    val title:String,
    val description:String,
    val time:String,
    val location:String
)