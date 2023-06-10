package com.like_magic.notes.domen.usecases

import com.like_magic.notes.domen.NotesRepository
import io.reactivex.Single

class GetLocationUseCase(private val repository: NotesRepository) {

    operator fun invoke(): Single<String?> = repository.getLocation()

}