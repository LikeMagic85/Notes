package com.like_magic.notes.data.mapper


import com.like_magic.notes.data.database.NoteDbModel
import com.like_magic.notes.domen.entity.NoteEntity

class NoteMapper {

    fun mapDbModelToEntity(noteDbModel: NoteDbModel):NoteEntity{
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

}