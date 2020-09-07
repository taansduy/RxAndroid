package com.example.rxandroid.di

import com.example.rxandroid.repository.MovieRepository
import com.example.rxandroid.service.ApiService
import com.example.rxandroid.service.TheMovieApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideApi(): TheMovieApi = ApiService.getClient()

    @Singleton
    @Provides
    fun provideMovieRepository(): MovieRepository = MovieRepository()
}