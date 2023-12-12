package com.kmm.shared.http

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

//android平台的HttpClient,使用OkHttp引擎
actual fun httpClientGet(plugin: HttpClientConfig<*>.() -> Unit): HttpClient {
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
        plugin()
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