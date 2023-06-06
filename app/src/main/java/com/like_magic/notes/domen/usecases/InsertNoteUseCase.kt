package com.like_magic.notes.domen.usecases

import com.like_magic.notes.domen.NotesRepository
import com.like_magic.notes.domen.entity.NoteEntity

class InsertNoteUseCase(private val repository: NotesRepository) {

    operator fun invoke(note:NoteEntity){
        repository.insertNote(note)
    }

}