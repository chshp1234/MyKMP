package com.kmm.shared.http

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.js.Js
import io.ktor.client.engine.js.JsClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

//js平台的HttpClient
//@JsExport
//@JsModule("")
actual fun httpClientGet(customPlugin: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(Js) {
        engine {

        }
        install(ContentNegotiation) { json() }
        customPlugin()
        /*install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
//            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }*/
    }
}