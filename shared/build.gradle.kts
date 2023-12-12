plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    kotlin("plugin.serialization") version "1.9.10"
//    id("kotlin-kapt")
}

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