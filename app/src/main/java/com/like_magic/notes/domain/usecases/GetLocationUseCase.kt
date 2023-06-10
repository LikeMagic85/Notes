package com.like_magic.notes.domain.usecases

import com.like_magic.notes.domain.NotesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val repository: NotesRepository) {

    operator fun invoke(): Single<String?> = repository.getLocation()

}