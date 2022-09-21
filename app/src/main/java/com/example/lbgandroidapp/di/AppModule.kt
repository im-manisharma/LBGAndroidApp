package com.example.lbgandroidapp.di

import com.example.lbgandroidapp.BuildConfig
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
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl() = "https://api.themoviedb.org/3/movie/"

    @ApiKey
    @Provides
    fun provideApiKey():String = BuildConfig.API_KEY

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create(
        GsonBuilder().setLenient().create()
    )

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient
            .Builder()
            .protocols(Collections.singletonList(Protocol.HTTP_1_1))
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
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
    fun provideMoviesApiService(retrofit: Retrofit): MoviesService = retrofit.create(MoviesService::class.java)

    @Provides
    fun provideMoviesRepository(
        api: MoviesService,
        mapper: MovieApiDataMapper
    ): MoviesRepository = MoviesRepositoryImpl(api, mapper)

    @Provides
    fun provideMovieDetailsRepository(
        api: MoviesService,
        mapper: MovieApiDataMapper
    ): MovieDetailsRepository = MovieDetailsRepositoryImpl(api, mapper)

    private companion object{
        const val READ_TIME_OUT = 30L //In Seconds
        const val WRITE_TIME_OUT = 30L //In Seconds
        const val CONNECT_TIME_OUT = 30L //In Seconds
    }
}