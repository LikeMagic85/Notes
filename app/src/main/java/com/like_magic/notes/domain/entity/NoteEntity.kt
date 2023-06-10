package com.like_magic.notes.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteEntity(
    val id: Int = UNCONFINED_ID,
    val title:String,
    val description:String,
    val time:String,
    val location:String = UNCONFINED_LOCATION
):Parcelable {
    companion object {
        const val UNCONFINED_ID = 0
        const val UNCONFINED_LOCATION = ""
    }
}

