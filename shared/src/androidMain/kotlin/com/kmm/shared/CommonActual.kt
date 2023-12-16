package com.kmm.shared

object AndroidPlatform : Platform {
    override val name: String
        get() = "AndroidPlatform"

}

actual fun getPlatform(): Platform {
    return AndroidPlatform
}

actual fun greeting(platform: Platform): String {
    return "Hello, Kotlin/${platform.name} platform!"
}