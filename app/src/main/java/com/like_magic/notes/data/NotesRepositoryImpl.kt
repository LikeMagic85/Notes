package com.like_magic.notes.data

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.location.LocationManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.like_magic.notes.data.database.AppDatabase
import com.like_magic.notes.data.mapper.NoteMapper
import com.like_magic.notes.domen.NotesRepository
import com.like_magic.notes.domen.entity.NoteEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class NotesRepositoryImpl(private val application: Application) : NotesRepository {

    private val notesDao = AppDatabase.getInstance(application).notesDao()
    private val mapper = NoteMapper(application)
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
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

    override fun deleteNote(noteId: Int) {
        Completable.fromCallable {
            notesDao.deleteNote(noteId)
        }.subscribeOn(Schedulers.io()).subscribe()
    }

    @SuppressLint("MissingPermission")
    override fun getLocation() =  Single.create { emitter ->
            val tokenSource = CancellationTokenSource()
            val token = tokenSource.token
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
            if(isLocationEnabled()){
                mFusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY, token).addOnCompleteListener {task->
                    emitter.onSuccess(task.result)
                }
            }
            else emitter.onError(Throwable("something went wrong"))
        }
        .map {
            mapper.mapLocationToCity(it)
    }.subscribeOn(Schedulers.io())


    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

}