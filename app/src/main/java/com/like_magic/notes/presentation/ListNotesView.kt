package com.like_magic.notes.presentation

import com.like_magic.notes.domen.entity.NoteEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ListNotesView : MvpView {

    fun showListNotes(list: List<NoteEntity>)
}

