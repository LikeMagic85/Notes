package com.like_magic.notes.presentation

import com.like_magic.notes.domain.entity.NoteEntity
import com.like_magic.notes.domain.entity.NoteEntity.Companion.UNCONFINED_LOCATION
import com.like_magic.notes.domain.usecases.DeleteNoteUseCase
import com.like_magic.notes.domain.usecases.GetLocationUseCase
import com.like_magic.notes.domain.usecases.InsertNoteUseCase
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
@InjectViewState
class NotePresenter @Inject constructor(
    private val insertNoteUseCase:InsertNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getLocationUseCase: GetLocationUseCase

    ): MvpPresenter<AppViews.NoteView>() {

    fun setScreenMode(mode:String, note: NoteEntity?){
        viewState.setRightScreenMode(mode, note)
    }

    fun insertNote(title:String, description:String, id:Int, location:String){
        if(title.isNotEmpty()){
            viewState.isLoading(true)
            if(location != UNCONFINED_LOCATION){
                val note = NoteEntity(
                    title = title,
                    description = description,
                    time = getCurrentTime(),
                    location = location,
                    id = id
                )
                insertNoteUseCase(note)
                viewState.isLoading(false)
                viewState.closeScreen()
            }else{
                val compositeDisposable = CompositeDisposable()
                val disposable = getLocationUseCase().subscribe { result ->
                    val note = result?.let {
                        NoteEntity(
                            title = title,
                            description = description,
                            time = getCurrentTime(),
                            location = it,
                            id = id
                        )
                    }
                    note?.let { insertNoteUseCase(it) }
                    viewState.isLoading(false)
                    viewState.closeScreen()
                }
                compositeDisposable.add(disposable)
            }
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