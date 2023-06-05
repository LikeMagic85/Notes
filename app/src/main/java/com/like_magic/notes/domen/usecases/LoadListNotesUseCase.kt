package com.like_magic.notes.domen.usecases

import com.like_magic.notes.domen.NotesRepository
import com.like_magic.notes.domen.entity.NoteEntity
import io.reactivex.Observable

class LoadListNotesUseCase(private val repository: NotesRepository) {

    operator fun invoke(): Observable<List<NoteEntity>> = repository.getNotesList()

}