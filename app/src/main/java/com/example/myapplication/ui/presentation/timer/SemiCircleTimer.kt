package com.example.myapplication.ui.presentation.timer

import android.util.Log
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
            .padding(horizontal = 64.dp, vertical = 24.dp),
        contentAlignment = Alignment.Center
    ) {

        val density = LocalDensity.current
        val weight = this.maxWidth
        val height = this.maxHeight

        Log.i(
            "Ninja",
            "dimens: $weight $height $density"
        )

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {

            val strokeWidth = 20 * density.density
            val padding = strokeWidth / 2

            val bottom = size.height - padding

            val theta = Math.toRadians(14.5)
            val sinTheta = kotlin.math.sin(theta).toFloat()

// 👇 constrain by BOTH width & height
            val maxRadiusFromHeight = (bottom - padding) / (1f + sinTheta)
            val maxRadiusFromWidth = (size.width - strokeWidth) / 2f

            val radius = minOf(maxRadiusFromWidth, maxRadiusFromHeight)
            val diameter = radius * 2

            val arcSize = Size(size.width - strokeWidth, diameter)


// center Y (so endpoints touch bottom)
            val centerY = bottom - radius * sinTheta

            val topLeft = Offset(
                x = strokeWidth / 2,
                y = centerY - radius
            )
            Log.i(
                "Ninja",
                "dimens: ${size.minDimension} ${size.height} ${size.width}"
            )

            // Background arc
            drawArc(
                color = Color.Gray.copy(alpha = 0.3f),
                startAngle = 165.5f,
                sweepAngle = 209f,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth,
                    cap = StrokeCap.Round
                ),
                topLeft = topLeft,
                size = arcSize
            )

            // Progress arc
            drawArc(
                color = Color.Blue,
                startAngle = 165.5f,
                sweepAngle = 209f * progress,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth,
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