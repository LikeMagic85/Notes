package com.like_magic.notes.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface NotesDao {
    @Query("SELECT * FROM notes_list")
    fun getAllNotes(): Observable<List<NoteDbModel>>

    @Query("SELECT * FROM notes_list WHERE id == :noteId")
    fun getNote(noteId:Int): Single<NoteDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteDbModel: NoteDbModel)

    @Query("DELETE  FROM notes_list WHERE id == :noteId")
    fun deleteNote(noteId:Int)

}