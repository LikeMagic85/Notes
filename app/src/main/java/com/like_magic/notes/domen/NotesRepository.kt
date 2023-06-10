package com.like_magic.notes.domen

import com.like_magic.notes.domen.entity.NoteEntity
import io.reactivex.Observable
import io.reactivex.Single

interface NotesRepository {

    fun getNotesList(): Observable<List<NoteEntity>>
    fun insertNote(noteEntity: NoteEntity)
    fun deleteNote(noteId:Int)

    fun getLocation(): Single<String?>

}