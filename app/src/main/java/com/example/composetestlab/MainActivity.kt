package com.example.composetestlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composetestlab.composelayouts.MySootheApp
import com.example.composetestlab.composestates.ComposeStatesDemo
import com.example.composetestlab.ui.theme.ComposeTestlabTheme

val dummyListAlign = listOf(
    "Align your body",
    "Align your mind",
    "Align your life",
    "Align your body",
)

val dummyListGrid = listOf(
    "Short mantras",
    "Stress and anxiety",
    "Overwhelmed",
    "Nature meditations",
    "Self-massage",
    "Nightly wind down",
)

class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestlabTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                     ComposeStateDemo()
//                    ComposeLayoutsDemo(activity = this@MainActivity)
                }
            }
        }
    }
}

@Composable
private fun ComposeStateDemo() {
    ComposeStatesDemo()
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
private fun ComposeLayoutsDemo(activity: MainActivity) {
    val windowSizeClass = calculateWindowSizeClass(activity = activity)
    MySootheApp(windowSizeClass = windowSizeClass)
}

