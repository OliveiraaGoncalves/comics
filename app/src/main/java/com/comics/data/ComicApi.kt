package com.comics.data

import com.comics.core_network.BuildConfig
import com.comics.core_network.handleProvider.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicApi {
    @GET("comics")
    suspend fun getComics(
        @Query("ts") ts: String = BuildConfig.TS,
        @Query("apikey") apiKey: String = BuildConfig.APIKEY,
        @Query("hash") hash: String = BuildConfig.HASH
    ): NetworkResponse<ItemModel>
}