package com.example.jfx

import com.kmm.shared.http.HttpGet
import javafx.fxml.FXML
import javafx.scene.control.Label
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

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