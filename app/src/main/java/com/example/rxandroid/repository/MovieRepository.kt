package com.example.rxandroid.repository

import androidx.lifecycle.MutableLiveData
import com.example.rxandroid.di.DaggerAppComponent
import com.example.rxandroid.model.Movie
import com.example.rxandroid.model.Response
import com.example.rxandroid.service.TheMovieApi
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieRepository {
    @Inject
    lateinit var api: TheMovieApi
    private var nowPlayingDisposable: Disposable? = null
    var nowPlayingResult: MutableLiveData<List<Movie>> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()
    fun getNowPlaying(page: Int) {
        nowPlayingDisposable?.dispose()
        api.doGetListNowPlayingMovies(page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Response> {
                override fun onComplete() {
                    nowPlayingDisposable?.dispose()
                }

                override fun onSubscribe(d: Disposable) {
                    nowPlayingDisposable = d
                }

                override fun onNext(t: Response) {
                    nowPlayingResult.value = t.results
                }

                override fun onError(e: Throwable) {
                    error.value = e.toString()
                }

            })
    }

    init {
        DaggerAppComponent.create().inject(this)
    }
}