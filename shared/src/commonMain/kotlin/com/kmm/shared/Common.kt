package com.kmm.shared


interface Platform {
    val name: String
}

object DefaultPlatform : Platform {
    override val name: String = "Multiplatform"
}

expect fun getPlatform(): Platform

expect fun greeting(platform: Platform = DefaultPlatform): String
