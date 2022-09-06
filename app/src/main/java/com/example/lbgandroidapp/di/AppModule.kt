package com.example.lbgandroidapp.di

import android.content.Context
import com.example.lbgandroidapp.R
import com.example.lbgandroidapp.data.datasource.MoviesService
import com.example.lbgandroidapp.data.mappers.MovieApiDataMapper
import com.example.lbgandroidapp.data.repository.MovieDetailsRepositoryImpl
import com.example.lbgandroidapp.data.repository.MoviesRepositoryImpl
import com.example.lbgandroidapp.domain.repository.MovieDetailsRepository
import com.example.lbgandroidapp.domain.repository.MoviesRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @BaseUrl
    @Provides
    @Singleton
    fun provideBaseUrl() = "https://api.themoviedb.org/3/movie/"

    @ApiKey
    @Provides
    @Singleton
    fun provideApiKey(@ApplicationContext context: Context):String = context.getString(R.string.tmdb_api_key)

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create(
        GsonBuilder().setLenient().create()
    )

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient
            .Builder()
            .protocols(Collections.singletonList(Protocol.HTTP_1_1))
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideMoviesApiService(retrofit: Retrofit): MoviesService = retrofit.create(MoviesService::class.java)

    @Singleton
    @Provides
    fun provideMoviesRepository(
        api: MoviesService,
        mapper: MovieApiDataMapper
    ): MoviesRepository = MoviesRepositoryImpl(api, mapper)

    @Singleton
    @Provides
    fun provideMovieDetailsRepository(
        api: MoviesService,
        mapper: MovieApiDataMapper
    ): MovieDetailsRepository = MovieDetailsRepositoryImpl(api, mapper)

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}