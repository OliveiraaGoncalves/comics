package com.comics.core_network.di

import com.comics.core_network.BuildConfig
import com.comics.core_network.adapter.NetworkResponseAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    val module = module {
        single(QualifierHost) {
            BuildConfig.HOST
        }

        single {
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        single(QualifierAuthInterceptor) {
            Interceptor { chain ->
                val newRequest =
                    chain.request()
                        .newBuilder()
                        .build()
                chain.proceed(newRequest)
            }
        }

        single<Interceptor>(QualifierLoggerInterceptor) {
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        }
        single {
            provideRetrofit(
                okHttpClient = get(),
                moshi = get(),
                baseUrl = get(QualifierHost)
            )
        }

        single {
            provideOkhttp(
                get(QualifierAuthInterceptor),
                get(QualifierLoggerInterceptor),
            )
        }
    }

    private fun provideOkhttp(
        vararg interceptor: Interceptor
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        interceptor.forEach {
            okHttpClientBuilder.addInterceptor(it)
        }
        return okHttpClientBuilder
            .readTimeout(240, TimeUnit.SECONDS)
            .connectTimeout(240, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(0, 5, TimeUnit.MINUTES))
            .protocols(listOf(Protocol.HTTP_1_1))
            .build()
    }

    private fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(NetworkResponseAdapterFactory(moshi))
            .build()
}