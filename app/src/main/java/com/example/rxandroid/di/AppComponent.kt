package com.example.rxandroid.di

import com.example.rxandroid.repository.MovieRepository
import com.example.rxandroid.viewmodel.MovieViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(repository: MovieRepository)

    fun inject(viewModel: MovieViewModel)
}