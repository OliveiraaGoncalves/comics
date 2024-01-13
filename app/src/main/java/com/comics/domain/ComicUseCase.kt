package com.comics.domain

import com.comics.data.ComicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ComicUseCase {
    suspend fun getAllComics(): Flow<List<Item>>
}

class ComicUseCaseImpl(
    private val repository: ComicRepository,
    private val mapper: ComicMapper
) : ComicUseCase {
    override suspend fun getAllComics(): Flow<List<Item>> =
        repository.getAllComics().map { resultModels ->
            resultModels.map {
                mapper.mapToItem(it)
            }
        }
}