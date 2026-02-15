package com.example.myapplication.ui.presentation.timer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


fun formatTime(ms: Long): String {
    val totalSeconds = (ms / 1000).toInt()
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}

@Composable
fun SemiCircleTimer(
    progress: Float,
    remainingMillis: Long,
    modifier: Modifier
) {

    BoxWithConstraints(
        modifier = modifier
            .background(
                color = Color.LightGray.copy(alpha = 0.3f),
                shape = RoundedCornerShape(26.dp)
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        val density = LocalDensity.current

        val canvasWidth = constraints.maxWidth.toFloat()
        val strokeWidthPx = canvasWidth * 0.08f // adaptive thickness

        Canvas(modifier = Modifier.fillMaxSize()) {

            val padding = strokeWidthPx / 2

            val arcSize = Size(
                width = size.width - 2 * padding,
                height = size.height - 2 * padding
            )

            val topLeft = Offset(padding, padding)

            // Background arc
            drawArc(
                color = Color.Gray.copy(alpha = 0.3f),
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = false,
                style = Stroke(
                    width = strokeWidthPx,
                    cap = StrokeCap.Round
                ),
                topLeft = topLeft,
                size = arcSize
            )

            // Progress arc
            drawArc(
                color = Color.Blue,
                startAngle = 180f,
                sweepAngle = 180f * progress,
                useCenter = false,
                style = Stroke(
                    width = strokeWidthPx,
                    cap = StrokeCap.Round
                ),
                topLeft = topLeft,
                size = arcSize
            )
        }

        // Center text
        Text(
            text = formatTime(remainingMillis),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}