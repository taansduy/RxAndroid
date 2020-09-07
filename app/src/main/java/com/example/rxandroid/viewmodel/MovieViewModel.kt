package com.example.rxandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rxandroid.di.DaggerAppComponent
import com.example.rxandroid.model.Movie
import com.example.rxandroid.repository.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel : ViewModel() {
    @Inject
    lateinit var repository: MovieRepository
    var result : MutableLiveData<List<Movie>>
    var error : MutableLiveData<String>
    fun getNowPlayingMovie(page: Int) = viewModelScope.launch {
        repository.getNowPlaying(page)
    }

    init {
        DaggerAppComponent.create().inject(this)
        result = repository.nowPlayingResult
        error = repository.error
    }
}