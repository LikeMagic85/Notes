package com.like_magic.notes.presentation

import com.like_magic.notes.domen.entity.NoteEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface AppViews {
    @StateStrategyType(AddToEndSingleStrategy::class)
    interface ListNotesView : MvpView {

        fun showListNotes(list: List<NoteEntity>)
    }

    @StateStrategyType(AddToEndSingleStrategy::class)
    interface NoteView : MvpView {

        fun setRightScreenMode(mode: String, note: NoteEntity?)
        fun showError(isError:Boolean)

        fun closeScreen()
    }
}

