import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

dependencies {
    compileOnly(libs.bundles.gradle)
}

gradlePlugin {
    plugins {
        register("libraryConvention") {
            id = libs.plugins.library.convention.get().pluginId
            implementationClass = "LibraryConventionPlugin"
        }
    }
}