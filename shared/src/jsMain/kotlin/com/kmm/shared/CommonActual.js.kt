package com.kmm.shared

import com.kmm.shared.bean.Location
import com.kmm.shared.http.HttpGet
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlin.js.Promise

object JsPlatform : Platform {
    override val name: String
        get() = "JsPlatform"
}

@OptIn(ExperimentalJsExport::class)
@JsExport
@JsName("getPlatform")
actual fun getPlatform(): Platform {
    return JsPlatform
}

@OptIn(ExperimentalJsExport::class)
@JsExport
@JsName("greeting")
actual fun greeting(platform: Platform): String {
    return platform.name
}

@OptIn(ExperimentalJsExport::class)
@JsExport
@JsName("getWeather")
fun getWeather(location: String): Promise<String> {
    return GlobalScope.promise {
        try {
            HttpGet.getWeather(location)
        } catch (e: Exception) {
            ""
        }
    }
}