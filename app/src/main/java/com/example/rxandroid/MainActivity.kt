package com.example.rxandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations.map
import androidx.viewbinding.ViewBinding
import com.example.rxandroid.databinding.ActivityMainBinding
import com.example.rxandroid.model.Movie
import com.example.rxandroid.view.adapter.MovieAdapter
import com.example.rxandroid.viewmodel.MovieViewModel
import rx.Observable
import rx.Subscriber
import rx.functions.Action1

class MainActivity : AppCompatActivity() {
    private val viewModel: MovieViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        viewModel.getNowPlayingMovie(1)
        adapter = MovieAdapter(arrayListOf()) {
        }
        binding.rcvMovie.adapter = adapter
        bindViewModel()
    }
    private fun bindViewModel() {
        viewModel.result.observe(this, Observer {
            adapter.addAll(it as ArrayList<Movie>)
        })
    }
}