package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {

    private val _totalTimeMillis = MutableStateFlow(0L)
    private val _remainingTimeMillis = MutableStateFlow(0L)

    val remainingTimeMillis: StateFlow<Long> = _remainingTimeMillis

    // progress: 1.0 -> full, 0.0 -> empty
    val progress: StateFlow<Float> = combine(
        _remainingTimeMillis, _totalTimeMillis
    ) { remaining, total ->
        if (total == 0L) 0f else remaining.toFloat() / total
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0f)

    fun startTimer(minutes: Int) {
        val totalMillis = minutes * 60 * 1000L
        _totalTimeMillis.value = totalMillis
        _remainingTimeMillis.value = totalMillis

        viewModelScope.launch {
            val interval = 1000L
            while (_remainingTimeMillis.value > 0) {
                delay(interval)
                _remainingTimeMillis.value -= interval
            }
        }
    }
}
