package com.like_magic.notes.presentation

import com.like_magic.notes.domain.usecases.DeleteNoteUseCase
import com.like_magic.notes.domain.usecases.LoadListNotesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class ListNotesPresenter @Inject constructor(
    val loadListNotesUseCase: LoadListNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase
) : MvpPresenter<AppViews.ListNotesView>() {


    fun getListNotes() {
        val compositeDisposable = CompositeDisposable()
        val disposable = loadListNotesUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewState.showListNotes(it)
            }
        compositeDisposable.add(disposable)
    }

    fun deleteNote(id: Int) {
        deleteNoteUseCase(id)
    }

}

