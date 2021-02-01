package com.example.viewmodel.ui.counter

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.viewmodel.R

class CounterSimpleViewModel {

    companion object {
        private const val SECOND = 1000L
    }

    private val _counterLiveData = MutableLiveData(0)
    private val _btnTitleLiveData = MutableLiveData(R.string.start_count)

    var isCounterRunning = false
        private set(value) {
            field = value
            _btnTitleLiveData.value = if (value) R.string.stop_count else R.string.start_count
        }

    private val handler = Handler(Looper.getMainLooper())

    private val tickRunnable = object : Runnable {
        override fun run() {
            _counterLiveData.value = (_counterLiveData.value ?: 0) + 1

            handler.postDelayed(this, SECOND)
        }
    }

    val counterLiveData: LiveData<Int> = _counterLiveData
    val btnTitleLiveData: LiveData<Int> = _btnTitleLiveData

    fun startCount() {
        if (isCounterRunning.not()) {
            handler.postDelayed(tickRunnable, SECOND)
            isCounterRunning = true
        }
    }

    fun stopCount() {
        if (isCounterRunning) {
            handler.removeCallbacks(tickRunnable)
            isCounterRunning = false
        }
    }
}