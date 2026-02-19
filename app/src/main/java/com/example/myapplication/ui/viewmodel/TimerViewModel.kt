package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TimerViewModel : ViewModel() {

    private val _totalTime = MutableStateFlow(0L)
    private val _endTime = MutableStateFlow(0L)

    val totalTime: StateFlow<Long> = _totalTime
    val endTime: StateFlow<Long> = _endTime

    fun startTimer(minutes: Int) {
        val total = minutes * 60 * 1000L
        _totalTime.value = total
        _endTime.value = System.currentTimeMillis() + total
    }
}
