package com.like_magic.notes.domain.usecases

import com.like_magic.notes.domain.NotesRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val repository: NotesRepository) {

    operator fun invoke(noteId:Int){
        repository.deleteNote(noteId)
    }

}