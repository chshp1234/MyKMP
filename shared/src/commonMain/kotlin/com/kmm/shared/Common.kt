package com.kmm.shared

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@OptIn(ExperimentalJsExport::class)
@JsExport
interface Platform {
    val name: String
}

object DefaultPlatform : Platform {
    override val name: String = "Multiplatform"
}

expect fun getPlatform(): Platform

expect fun greeting(platform: Platform = DefaultPlatform): String

//Function 'greeting': expect and corresponding actual are declared in the same module,
//which will be prohibited in Kotlin 2.0. See https://youtrack.jetbrains.com/issue/KT-55177
//actual fun greeting(platform: Platform): String {
//    return "Hello, Kotlin/${platform.name} platform!"
//}
