package com.kmm.shared

object JVMPlatform : Platform {
    override val name: String
        get() = "JVMPlatform"

}

actual fun getPlatform(): Platform {
    return JVMPlatform
}

actual fun greeting(platform: Platform): String {
    return "Hello, Kotlin/${platform.name} platform!"
}