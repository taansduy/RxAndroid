package com.example.rxandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Transformations.map
import rx.Observable
import rx.Subscriber
import rx.functions.Action1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myObservable = Observable.create(
            Observable.OnSubscribe<String> {
                Log.d("Thread1", Thread.currentThread().name)
                it.onNext("Hello World")
                it.onCompleted()
            }
        )
        val myListObservable = Observable.create(Observable.OnSubscribe<List<String>> {
            it.onNext(arrayListOf("a", "b", "c"))
        })
        val mySubscriber = object : Subscriber<String>() {
            override fun onNext(t: String?) {
                Log.d("Thread", Thread.currentThread().name)
                Toast.makeText(this@MainActivity, t, Toast.LENGTH_SHORT).show()
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }

        }
        myObservable.subscribe(mySubscriber)
        val callAction = Action1<String> {
            Log.d("Thread", Thread.currentThread().name)
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
        }
        myObservable.subscribe(callAction)
        Observable.just("Hello,world!").map {
            it.hashCode()
        }.subscribe {
            Log.d("Thread", Thread.currentThread().name)
            Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_SHORT).show()
        }

        myListObservable.flatMap {
            Observable.from(it)
        }.filter { it != "b" }.subscribe {
            Log.d("Time", System.currentTimeMillis().toString())
            Log.d("Tim", it)
        }
    }

}