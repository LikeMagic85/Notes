package com.like_magic.notes

import android.app.Application
import com.like_magic.notes.di.DaggerAppComponent

internal class NotesApp:Application(){
    val component by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }
}