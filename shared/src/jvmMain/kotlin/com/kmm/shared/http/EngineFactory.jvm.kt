package com.kmm.shared.http

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

//jvm平台的HttpClient,使用OkHttp引擎
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
//            addNetworkInterceptor(networkInterceptor)

            preconfigured = preClient
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
//            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }
        customPlugin()
    }
}

class ClientInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
//        builder.addHeader(
//            "Accept",
//            "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"
//        )
//        builder.addHeader("Accept-Encoding", "gzip, deflate, br")
//        builder.addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
//        builder.addHeader("Cache-Control", "max-age=0")
//        builder.addHeader(
//            "Sec-Ch-Ua",
//            """"Not_A Brand";v="8", "Chromium";v="120", "Microsoft Edge";v="120""""
//        )
//        builder.addHeader("Sec-Ch-Ua-Mobile", "?0")
//        builder.addHeader("Sec-Ch-Ua-Platform", """"macOS"""")
//        builder.addHeader("Sec-Fetch-Dest", "document")
//        builder.addHeader("Sec-Fetch-Mode", "navigate")
//        builder.addHeader("Sec-Fetch-Site", "none")
//        builder.addHeader("Sec-Fetch-User", "?1")
//        builder.addHeader("Upgrade-Insecure-Requests", "1")
        return chain.proceed(builder.build())
    }
}

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}