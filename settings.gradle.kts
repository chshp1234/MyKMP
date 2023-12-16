pluginManagement {
    repositories {
        maven("https://maven.aliyun.com/repository/public")
        maven("https://maven.aliyun.com/repository/google")
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        mavenLocal()
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
dependencyResolutionManagement {

    //设置这个会导致在kmp中添加ios()目标时,任务构建失败(Build was configured to prefer settings repositories over project repositories but repository 'ivy' was added by build file 'shared/build.gradle.kts')
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
        maven("https://maven.aliyun.com/repository/public")
        maven("https://maven.aliyun.com/repository/google")
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        mavenLocal()
        google()
        mavenCentral()
    }
}

rootProject.name = "My Application"
include(":app")
include(":shared")
include(":jfx")
