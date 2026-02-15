import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.presentation.timer.AnimatedProgress
import com.example.myapplication.ui.presentation.timer.SemiCircleTimer
import com.example.myapplication.ui.viewmodel.TimerViewModel

@Composable
fun MainScreen(
    viewModel: TimerViewModel = viewModel()
) {

    var minutes by remember { mutableIntStateOf(2) }

    val progress by viewModel.progress.collectAsState()
    val remaining by viewModel.remainingTimeMillis.collectAsState()

    val animatedProgress = AnimatedProgress(progress)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row {
            Button(onClick = {
                minutes = 1
                viewModel.startTimer(1)
            }) {
                Text("1 min")
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(onClick = {
                minutes = 2
                viewModel.startTimer(2)
            }) {
                Text("2 min")
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        SemiCircleTimer(
            progress = animatedProgress,
            remainingMillis = remaining,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(24.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            viewModel.startTimer(minutes)
        }) {
            Text("Restart")
        }
    }
}

