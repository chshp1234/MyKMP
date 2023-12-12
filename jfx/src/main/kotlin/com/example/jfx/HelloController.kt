package com.example.jfx

import com.kmm.shared.greeting
import com.kmm.shared.http.HttpGet
import javafx.fxml.FXML
import javafx.scene.control.Label
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class HelloController {

    @FXML
    private lateinit var welcomeText: Label

    @FXML
    private fun onHelloButtonClick() {
        MainScope().launch {
            welcomeText.text = HttpGet.getWeather()
        }
    }
}