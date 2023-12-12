package com.kmm.shared.bean

import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    val results: List<Result>
)

@Serializable
data class Result(
    val last_update: String,
    val location: Location,
    val now: Now
)

@Serializable
data class Location(
    val country: String,
    val id: String,
    val name: String,
    val path: String,
    val timezone: String,
    val timezone_offset: String
)

@Serializable
data class Now(
    val code: String,
    val temperature: String,
    val text: String
)