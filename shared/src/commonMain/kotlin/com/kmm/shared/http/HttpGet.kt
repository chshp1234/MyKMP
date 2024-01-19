package com.kmm.shared.http

import com.kmm.shared.bean.RocketLaunch
import com.kmm.shared.bean.Weather
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpGet {
    private val client = httpClientGet {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend fun lastSuccessLaunch(): String {
        val rockets: List<RocketLaunch> =
            client.get("https://api.spacexdata.com/v4/launches") {

            }.body()

        val lastSuccessLaunch = rockets.last { it.launchSuccess == true }
        return "${lastSuccessLaunch.missionName}:${lastSuccessLaunch.launchDateUTC}->${lastSuccessLaunch.launchSuccess}"
    }

    suspend fun getWeather(location: String = "北京"): String {
        val weather = client.get("https://api.seniverse.com/v3/weather/now.json") {
            this.parameter("key", "Sf36ujG-QgU65jF5l")
            this.parameter("location", location)
        }.body<Weather>()
        return weather.results[0].now.let {
            "$location:${it.text}/${it.temperature}摄氏度"
        }
    }
}