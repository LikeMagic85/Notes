package com.like_magic.notes.di

import android.app.Application
import com.like_magic.notes.presentation.ListNotesFragment
import com.like_magic.notes.presentation.NoteFragment
import dagger.BindsInstance
import dagger.Component


@Component(modules = [DataModule::class])
interface AppComponent {

    fun inject(fragment: NoteFragment)

    fun inject(fragment: ListNotesFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ):AppComponent
    }
}