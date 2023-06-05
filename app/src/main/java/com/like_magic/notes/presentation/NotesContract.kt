package com.like_magic.notes.presentation

import com.like_magic.notes.domen.entity.NoteEntity


interface NotesContract {

    interface ListNotesView {
        fun showListNotes(list:List<NoteEntity>)
    }

    interface MainPresenter{
        fun attach(view:ListNotesView)
        fun detach()
        fun getListNotes()
    }

}