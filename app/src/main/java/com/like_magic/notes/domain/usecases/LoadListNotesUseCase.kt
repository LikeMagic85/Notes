package com.like_magic.notes.domain.usecases

import com.like_magic.notes.domain.NotesRepository
import com.like_magic.notes.domain.entity.NoteEntity
import io.reactivex.Observable
import javax.inject.Inject

class LoadListNotesUseCase @Inject constructor(private val repository: NotesRepository) {

    operator fun invoke(): Observable<List<NoteEntity>> = repository.getNotesList()

}