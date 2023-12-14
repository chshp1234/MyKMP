package com.kmm.shared.http

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig

//expect预期函数
//不同平台可以有不同的实现
//这里根据不同平台的请求引擎返回不同的HttpClient
expect fun httpClientGet(customPlugin: HttpClientConfig<*>.() -> Unit): HttpClient