package com.like_magic.notes.domen

import com.like_magic.notes.domen.entity.NoteEntity
import io.reactivex.Observable

interface NotesRepository {

    fun getNotesList(): Observable<List<NoteEntity>>
    fun addNote(noteEntity: NoteEntity)
    fun getNote(noteEntity: NoteEntity):NoteEntity
    fun deleteNote(noteId:Int)
    fun editNote(noteEntity: NoteEntity)

}