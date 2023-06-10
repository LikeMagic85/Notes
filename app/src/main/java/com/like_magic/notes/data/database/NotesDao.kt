package com.like_magic.notes.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable


@Dao
interface NotesDao {
    @Query("SELECT * FROM notes_list")
    fun getAllNotes(): Observable<List<NoteDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteDbModel: NoteDbModel)

    @Query("DELETE  FROM notes_list WHERE id == :noteId")
    fun deleteNote(noteId:Int)

}