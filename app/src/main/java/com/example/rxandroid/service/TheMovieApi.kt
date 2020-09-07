package com.example.rxandroid.service

import com.example.rxandroid.model.Response
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi{
    @GET("movie/now_playing")
    fun doGetListNowPlayingMovies(@Query("page") page: Int = 1): Observable<Response>
}