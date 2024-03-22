package com.kmm.shared

import com.kmm.shared.http.HttpGet
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.browser.*
import kotlinx.coroutines.*
import kotlinx.html.*
import kotlinx.html.div
import kotlinx.html.dom.*
import kotlinx.html.js.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.w3c.dom.HTMLInputElement
import kotlin.coroutines.*

fun main() {
    document.addEventListener("DOMContentLoaded", {
        Application().start()
    })
}

class Application : CoroutineScope {
    private val body get() = document.body!!
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job

    fun start() {
        body.append.div("content") {
            div {
                label {
                    +greeting(getPlatform())
                }
            }
            div {
                input(classes = "location") {
                    id = "location"
                    placeholder = "请输入地址"
                }
            }
            div {
                button {
                    +"查询天气"
                    onClickFunction = {
                        var responseText = ""
                        val input = document.getElementById("location")!!
                        //在浏览器中输出此类，查看其类型为HTMLInputElement
                        console.log(input::class)
                        //转换成HTMLInputElement类型，并获取输入的value值
                        //注意：input.nodeValue无法获取到输入的值
                        val mLocation = (input as HTMLInputElement).value
                        val job = launch {
                            responseText = HttpGet.getWeather(mLocation)
                        }
                        job.invokeOnCompletion {
                            console.log(responseText)
                            window.alert(responseText)
                        }
                    }
                }
            }
        }
    }
}

suspend fun saveCustomer(): String {
    val client = HttpClient(Js) {
        install(ContentNegotiation) { json(Json) }
    }
    val response: HttpResponse = client.post("http://0.0.0.0:8080/customer") {
        contentType(ContentType.Application.Json)
        setBody(Customer(3, "Jet", "Brains"))
    }
    return response.bodyAsText()
}

@Serializable
data class Customer(val id: Int, val firstName: String, val lastName: String)