package com.like_magic.notes.domain.usecases

import com.like_magic.notes.domain.NotesRepository
import com.like_magic.notes.domain.entity.NoteEntity
import javax.inject.Inject

class InsertNoteUseCase @Inject constructor(private val repository: NotesRepository) {

    operator fun invoke(note:NoteEntity){
        repository.insertNote(note)
    }

}