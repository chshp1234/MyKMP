package com.kmm.shared

object iOSPlatform : Platform {
    override val name: String
        get() = "iOSPlatform"

}

actual fun getPlatform(): Platform {
    return iOSPlatform
}

actual fun greeting(platform: Platform): String {
    return "Hello, Kotlin/${platform.name} platform!"
}