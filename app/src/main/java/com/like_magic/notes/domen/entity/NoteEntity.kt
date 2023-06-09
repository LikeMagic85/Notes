package com.like_magic.notes.domen.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteEntity(
    val id: Int = UNCONFINED_ID,
    val title:String,
    val description:String,
    val time:String,
    val location:String
):Parcelable {
    companion object {
        const val UNCONFINED_ID = 0
    }
}

