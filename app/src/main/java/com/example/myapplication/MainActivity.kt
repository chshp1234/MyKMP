package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.kmm.shared.greeting
import com.kmm.shared.http.HttpGet
import com.kmm.shared.http.HttpGet.getWeather
import com.kmm.shared.http.HttpGet.lastSuccessLaunch
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Greeting(greeting())
                        GetTmp()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        fontSize = 20.sp
    )
}

@Composable
fun GetTmp(modifier: Modifier = Modifier) {
    var state by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = true) {
        state = getWeather()
        isLoading = false
    }

    //根据加载状态,判断是加载进度条还是显示结果文字
    if (isLoading) {
        LoadingText()
    } else {
        Text(text = state, modifier = modifier, fontSize = 20.sp)
    }
}

@Composable
fun LoadingText() {
    var state by remember { mutableStateOf("") }
    val dot = remember { arrayOf("", ".", "..", "...") }

    LaunchedEffect(key1 = true) {
        var index = 0
        while (true) {
            state = "loading${dot[(++index) % 4]}"
            delay(500)
        }
    }

    Dialog(onDismissRequest = { }) {
        Text(text = state, Modifier, fontSize = 30.sp)
    }
}