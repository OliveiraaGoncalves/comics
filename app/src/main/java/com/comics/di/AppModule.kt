package com.comics.di

import android.content.res.Resources
import com.comics.core_network.di.NetworkModule
import com.comics.data.ComicApi
import com.comics.data.ComicRepository
import com.comics.data.ComicRepositoryImpl
import com.comics.domain.ComicMapper
import com.comics.domain.ComicMapperImpl
import com.comics.domain.ComicUseCase
import com.comics.domain.ComicUseCaseImpl
import com.comics.presentation.ComicViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object AppModule {
    val module = module {
        single<Resources> { androidContext().resources }
    }
    val list = module + NetworkModule.module
}