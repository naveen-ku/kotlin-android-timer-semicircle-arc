package com.example.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TimerScreen(
    minutes: Int,
    viewModel: TimerViewModel = viewModel()
) {
    val progress by viewModel.progress.collectAsState()
    val remaining by viewModel.remainingTimeMillis.collectAsState()

    val animatedProgress = AnimatedProgress(progress)

    LaunchedEffect(Unit) {
        viewModel.startTimer(minutes)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        SemiCircleTimer(
            progress = animatedProgress,
            remainingMillis = remaining,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f) // ensures semi-circle shape
        )
    }
}
