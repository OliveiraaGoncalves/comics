package com.comics.data

import com.comics.core_network.handleProvider.toFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ComicRepository {
    suspend fun getAllComics(): Flow<List<ResultModel>>
}

class ComicRepositoryImpl(
    private val service: ComicApi
) : ComicRepository {
    override suspend fun getAllComics(): Flow<List<ResultModel>> {
        return service.getComics().toFlow().map {
            it.data.results
        }
    }
}