package com.like_magic.notes.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.like_magic.notes.data.NotesRepositoryImpl
import com.like_magic.notes.data.database.AppDatabase
import com.like_magic.notes.data.database.NotesDao
import com.like_magic.notes.domain.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindRepository(impl:NotesRepositoryImpl):NotesRepository

    companion object {

        @Provides
        fun provideCoinInfoDao(application: Application):NotesDao{
            return AppDatabase.getInstance(application).notesDao()
        }

        @Provides
        fun provideFusedLocationProviderClient(
            application: Application
        ): FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(application)
    }
}