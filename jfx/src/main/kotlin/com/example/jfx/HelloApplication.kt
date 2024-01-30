package com.example.jfx

import com.example.jfx.aop.injectDeque
import com.example.jfx.aop.injectViewEventHandler
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class HelloApplication : Application() {

    init {
        injectDeque()
    }

    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("hello-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 320.0, 240.0)
        stage.title = "Hello!"
        stage.scene = scene
        stage.show()

        injectViewEventHandler(scene)
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}