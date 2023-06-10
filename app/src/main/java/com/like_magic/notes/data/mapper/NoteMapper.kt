package com.like_magic.notes.data.mapper


import android.app.Application
import android.location.Geocoder
import android.location.Location
import com.like_magic.notes.data.database.NoteDbModel
import com.like_magic.notes.domain.entity.NoteEntity
import javax.inject.Inject

class NoteMapper @Inject constructor(private val application: Application) {

    private fun mapDbModelToEntity(noteDbModel: NoteDbModel):NoteEntity{
        return NoteEntity(
            id = noteDbModel.id,
            title = noteDbModel.title,
            description = noteDbModel.description,
            time = noteDbModel.time,
            location = noteDbModel.location
        )
    }

    fun mapListDbModelToListEntity(list: List<NoteDbModel>):List<NoteEntity>{
        return mutableListOf<NoteEntity>().apply {
            list.forEach {
                add(mapDbModelToEntity(it))
            }
        }.toList()
    }

    fun mapEntityToDbModel(noteEntity: NoteEntity):NoteDbModel {
        return NoteDbModel(
            id = noteEntity.id,
            title = noteEntity.title,
            description = noteEntity.description,
            time = noteEntity.time,
            location = noteEntity.location
        )
    }

    fun mapLocationToCity(location:Location):String? {
        val geocoder = Geocoder(application)
        return geocoder.getFromLocation(location.latitude, location.longitude, 1)?.get(0)?.subAdminArea

    }


}