plugins {
    //为了生成 Android 库，除了 Kotlin Multiplatform 之外，还使用了单独的 Gradle 插件：
    id("com.android.library")
    //使用应用了 Kotlin Multiplatform 插件的 Gradle 构建系统
    //kotlin-multiplatform插件可配置项目以创建可在多个平台上工作的应用程序或库， 并为在这些平台上构建做好准备
    id("org.jetbrains.kotlin.multiplatform")
    //kotlin序列号插件
    kotlin("plugin.serialization") version "1.9.21"
//    id("kotlin-kapt")
}

//Android 库的配置,同普通aar模块
android {
    namespace = "com.kmm.shared"
    compileSdk = 34

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    val ktor_version = "2.3.7"
    val kotlinx_coroutines = "1.7.1"

    //设置编译目标
    //只有设置了编译目标,才能设置下面的源码集,并且才能创建对应的源码目录
    jvm()
    androidTarget()
//    iosArm64()
//    js()

    //源码集
    //可以设置单独的依赖关系
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
                implementation("io.ktor:ktor-client-logging:$ktor_version")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinx_coroutines")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinx_coroutines")
            }
        }
        val jvmMain by getting {
            // ...
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-javafx:$kotlinx_coroutines")
            }
        }
    }
}

dependencies {

}