package com.like_magic.notes.presentation

import android.app.Application
import com.like_magic.notes.data.NotesRepositoryImpl
import com.like_magic.notes.domen.usecases.LoadListNotesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter

class MainPresenter(application: Application):MvpPresenter<ListNotesView>() {

    private val repository = NotesRepositoryImpl(application)

    private val loadListNotesUseCase = LoadListNotesUseCase(repository)


    fun getListNotes(){
        val compositeDisposable = CompositeDisposable()
        val disposable = loadListNotesUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                viewState.showListNotes(it)
            }
        compositeDisposable.add(disposable)
    }


}

