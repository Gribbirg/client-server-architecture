rootProject.name = "client-server-architecture"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("gradle/libs.toml"))
        }
    }

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

includeBuild("build-logic")
include("practice:practice1")
include("practice:practice2:server")
include("practice:practice2:client")
include("practice:practice3")
include("practice:practice4")
include("practice:practice5")
include("practice:practice6")
include("core")
