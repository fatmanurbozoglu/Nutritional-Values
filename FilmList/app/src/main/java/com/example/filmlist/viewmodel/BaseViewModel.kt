package com.example.filmlist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


// coroutine ile ilgili seyler buraya yazılacak
open class BaseViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main // arka planda islemleri yapıp main e geri dönecek

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}