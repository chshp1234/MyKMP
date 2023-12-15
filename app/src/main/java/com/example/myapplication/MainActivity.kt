package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.window.SecureFlagPolicy
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
    val coroutineScope = rememberCoroutineScope()

    /*DisposableEffect(key1 =1){
        state = getWeather()
        onDispose {

        }
    }*/

    LaunchedEffect(key1 = true) {
        state = getWeather()
        isLoading = false
    }

    DarkBackground(isDark = isLoading)
    //根据加载状态,判断是加载进度条还是显示结果文字
    if (isLoading) {
        LoadingText()
    } else {
        Text(text = state, fontSize = 20.sp, modifier = modifier.clickable {
            coroutineScope.launch {
                isLoading = true
                state = getWeather()
                isLoading = false
            }
        })
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

    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(securePolicy = SecureFlagPolicy.Inherit)
    ) {
        //无法全屏,左右有空隙
        //解决方案:改用Popup组件
        /*Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.Black.copy(alpha = 0.5f))
                .clickable { }) {*/
        Text(text = state, Modifier, fontSize = 30.sp, color = Color.White)
//        }
    }
}

//令窗口背景变暗
@Composable
fun DarkBackground(isDark: Boolean) {
    val curView = LocalView.current
    /* 更改dialog window的透明度 */
    LaunchedEffect(isDark) {
        /**
         * 此代码逻辑来自 ComposeInputMethodManager.kt 下的[androidx.compose.foundation.text2.service.ImmHelper30]
         */
        tailrec fun Context.findWindow(): Window? = when (this) {
            is Activity -> window
            is ContextWrapper -> baseContext.findWindow()
            else -> null
        }

        fun View.findWindow(): Window? =
            (parent as? DialogWindowProvider)?.window ?: context.findWindow()
        try {
            val window = curView.findWindow() ?: return@LaunchedEffect
            val lp = window.attributes
            lp.dimAmount = if (isDark) 0.5f else 1f
            lp.alpha = if (isDark) 0.5f else 1f
            window.attributes = lp
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}