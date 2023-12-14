package com.kmm.shared.http

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.MessageLengthLimitingLogger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

//android平台的HttpClient,使用OkHttp引擎
actual fun httpClientGet(customPlugin: HttpClientConfig<*>.() -> Unit): HttpClient {
    val clientInterceptor = ClientInterceptor()
    val networkInterceptor = NetworkInterceptor()
    val preClient: OkHttpClient? = null

    return HttpClient(OkHttp) {
        engine {
            // this: OkHttpConfig
            config {
                // this: OkHttpClient.Builder
                followRedirects(true)
                // ...
            }

            addInterceptor(clientInterceptor)
            addNetworkInterceptor(networkInterceptor)

            preconfigured = preClient
        }
        install(Logging) {

            //android打上印日志
            logger = MessageLengthLimitingLogger(delegate = object : Logger {
                override fun log(message: String) {
                    Log.d("NetLog", message)
                }
            })
            level = LogLevel.ALL
            logger
//            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }
        customPlugin()
    }
}

class ClientInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}