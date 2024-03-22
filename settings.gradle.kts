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
        maven("https://www.jitpack.io")

        mavenLocal()
        google()
        mavenCentral()

        maven("https://maven.aliyun.com/repository/public")
        maven("https://maven.aliyun.com/repository/google")
        maven("https://maven.aliyun.com/repository/gradle-plugin")

    }
}

//解决问题：error package.json: Name contains illegal characters，* What went wrong:
//Execution failed for task ':kotlinNpmInstall'.
//>                 Process 'Resolving NPM dependencies using yarn' returns 1
//
//                  yarn install v1.21.1
//  info Visit https://yarnpkg.com/en/docs/cli/install for documentation about this command.
//
//https://stackoverflow.com/questions/60534770/exception-when-building-a-kotlin-js-project-error-package-json-name-contains-i
rootProject.name = "MyApplication"
include(":app")
include(":shared")
include(":jfx")
