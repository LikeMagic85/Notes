package com.like_magic.notes.presentation

import android.app.Application
import com.like_magic.notes.data.NotesRepositoryImpl
import com.like_magic.notes.domen.entity.NoteEntity
import com.like_magic.notes.domen.usecases.InsertNoteUseCase
import moxy.MvpPresenter

class NotePresenter(application: Application): MvpPresenter<AppViews.NoteView>() {

    private val repository = NotesRepositoryImpl(application)
    private val insertNoteUseCase = InsertNoteUseCase(repository)

    fun setScreenMode(mode:String, note: NoteEntity?){
        viewState.setRightScreenMode(mode, note)
    }

    fun insertNote(note:NoteEntity){
        insertNoteUseCase(note)
    }
}