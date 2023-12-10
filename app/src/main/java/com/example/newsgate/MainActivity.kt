package com.example.newsgate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.newsgate.news.domain.syncManger.MyBackgroundWorker
import com.example.newsgate.news.navigation.AppScreen
import com.example.newsgate.ui.theme.NewsGateTheme
import org.koin.android.ext.android.inject
import org.koin.core.component.inject
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsGateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    AppScreen()
                }
            }
        }
        val workerConstraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)

        val scheduledMessageWorker = OneTimeWorkRequest.Builder(
            MyBackgroundWorker::class.java,
        )
            .setConstraints(workerConstraints.build())
            .setInitialDelay(8, TimeUnit.HOURS)
            .build()

        val workManager by inject<WorkManager>()
        workManager.enqueue(scheduledMessageWorker)
    }
}
