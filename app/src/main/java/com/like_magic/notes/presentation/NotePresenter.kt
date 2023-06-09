package com.like_magic.notes.presentation

import android.app.Application
import com.like_magic.notes.data.NotesRepositoryImpl
import com.like_magic.notes.domen.entity.NoteEntity
import com.like_magic.notes.domen.usecases.DeleteNoteUseCase
import com.like_magic.notes.domen.usecases.InsertNoteUseCase
import moxy.MvpPresenter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NotePresenter(application: Application): MvpPresenter<AppViews.NoteView>() {

    private val repository = NotesRepositoryImpl(application)
    private val insertNoteUseCase = InsertNoteUseCase(repository)
    private val deleteNoteUseCase = DeleteNoteUseCase(repository)

    fun setScreenMode(mode:String, note: NoteEntity?){
        viewState.setRightScreenMode(mode, note)
    }

    fun insertNote(title:String, description:String, id:Int){
        if(title.isNotEmpty()){
            val note = NoteEntity(
                title = title,
                description = description,
                time = getCurrentTime(),
                location = "",
                id = id
            )
            insertNoteUseCase(note)
            viewState.closeScreen()
        }else{
            viewState.showError(true)
        }
    }

    fun deleteNote(id:Int){
        viewState.closeScreen()
        deleteNoteUseCase(id)
    }

    private fun getCurrentTime():String {
        val date = Date()
        return SimpleDateFormat("d MMMM yyyy, hh:mm", Locale.getDefault()).format(date)
    }
}