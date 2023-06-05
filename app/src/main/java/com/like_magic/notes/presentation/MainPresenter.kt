package com.like_magic.notes.presentation

import android.app.Application
import com.like_magic.notes.data.NotesRepositoryImpl
import com.like_magic.notes.domen.usecases.LoadListNotesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(application: Application):NotesContract.MainPresenter {

    private val repository = NotesRepositoryImpl(application)

    private val loadListNotesUseCase = LoadListNotesUseCase(repository)

    private var listNotesView: NotesContract.ListNotesView? = null

    override fun attach(view: NotesContract.ListNotesView) {
        this.listNotesView = view
    }

    override fun detach() {
        listNotesView = null
    }

    override fun getListNotes(){
        val compositeDisposable = CompositeDisposable()
        val disposable = loadListNotesUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                listNotesView?.showListNotes(it)
            }
        compositeDisposable.add(disposable)
    }


}

