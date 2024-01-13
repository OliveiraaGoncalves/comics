package com.comics.di

import com.comics.data.ComicApi
import com.comics.data.ComicRepository
import com.comics.data.ComicRepositoryImpl
import com.comics.domain.ComicMapper
import com.comics.domain.ComicMapperImpl
import com.comics.domain.ComicUseCase
import com.comics.domain.ComicUseCaseImpl
import com.comics.presentation.ComicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object ComicModule {
    val module = module {
        viewModel {
            ComicViewModel(
                useCase = get(),
            )
        }

        factory<ComicUseCase> {
            ComicUseCaseImpl(
                repository = get(),
                mapper = get()
            )
        }

        factory<ComicRepository> {
            ComicRepositoryImpl(
                service = get(),
            )
        }

        factory<ComicMapper> {
            ComicMapperImpl()
        }

        factory { get<Retrofit>().create(ComicApi::class.java) }
    }
}