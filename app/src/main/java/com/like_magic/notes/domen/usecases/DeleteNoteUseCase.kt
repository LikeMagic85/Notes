package com.like_magic.notes.domen.usecases

import com.like_magic.notes.domen.NotesRepository

class DeleteNoteUseCase(private val repository: NotesRepository) {

    operator fun invoke(noteId:Int){
        repository.deleteNote(noteId)
    }

}