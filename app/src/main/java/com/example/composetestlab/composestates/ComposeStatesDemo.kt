package com.example.composetestlab.composestates

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ComposeStatesDemo() {
    WaterCounter(modifier = Modifier.padding(16.dp))
}

@Composable
private fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        val count: MutableState<Int> = remember { mutableStateOf(0) }
        Text(
            text = "You've had ${count.value} glasses.",
            modifier = modifier.padding(16.dp)
        )
        Button(onClick = { count.value++ }, modifier.padding(top = 8.dp)) {
            Text("Add one")
        }
    }
}