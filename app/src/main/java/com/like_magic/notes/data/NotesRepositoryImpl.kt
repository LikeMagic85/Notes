package com.like_magic.notes.data

import android.app.Application
import com.like_magic.notes.data.database.AppDatabase
import com.like_magic.notes.data.mapper.NoteMapper
import com.like_magic.notes.domen.NotesRepository
import com.like_magic.notes.domen.entity.NoteEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class NotesRepositoryImpl(application: Application) : NotesRepository {

    private val notesDao = AppDatabase.getInstance(application).notesDao()
    private val mapper = NoteMapper()
    override fun getNotesList(): Observable<List<NoteEntity>> {
        return notesDao.getAllNotes()
            .subscribeOn(Schedulers.io())
            .map {
                mapper.mapListDbModelToListEntity(it)
            }

    }

    override fun insertNote(noteEntity: NoteEntity) {
        Completable.fromCallable {
            notesDao.insertNote(mapper.mapEntityToDbModel(noteEntity))
        }.subscribeOn(Schedulers.io()).subscribe()
    }

    override fun getNote(noteEntity: NoteEntity): NoteEntity {
        TODO("Not yet implemented")
    }

    override fun deleteNote(noteId: Int) {
        Completable.fromCallable {
            notesDao.deleteNote(noteId)
        }.subscribeOn(Schedulers.io()).subscribe()
    }

}