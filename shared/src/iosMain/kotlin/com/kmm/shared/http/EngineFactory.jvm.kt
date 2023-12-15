package com.kmm.shared.http

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging


actual fun httpClientGet(customPlugin: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(Darwin) {
        engine {
            configureRequest {
                setAllowsCellularAccess(true)
            }
        }
        customPlugin()
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
//            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }
    }
}