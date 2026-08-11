import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class FeatureConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {
            pluginManager.apply {
                apply(libs().getPlugin("android-library"))
                apply(libs().getPlugin("kotlin-android"))
                apply(libs().getPlugin("kotlin-compose"))
            }

            extensions.configure<LibraryExtension> {
                compileSdk = 35

                defaultConfig {
                    minSdk = 24
                    targetSdk = 35

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                    // добавляем настройку только для модулей с consumer-rules.pro
                    val moduleConsumer = file("consumer-rules.pro")
                    if (moduleConsumer.exists()) {
                        consumerProguardFiles("consumer-rules.pro")
                    }
                }

                buildTypes {

                    getByName("release") {
                        isMinifyEnabled = false

                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }

                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_11
                    targetCompatibility = JavaVersion.VERSION_11
                }

                buildFeatures {
                    compose = true
                }
            }

            extensions.configure<KotlinAndroidProjectExtension> {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_11)
                }
            }

            dependencies.apply {
                add("implementation", libs().getLibrary("androidx-core-ktx"))

                add("implementation", platform(libs().getLibrary("androidx-compose-bom")))
                add("implementation", libs().getLibrary("androidx-material3"))
                add("implementation", libs().getLibrary("koin-compose"))

                add("debugImplementation", libs().getLibrary("androidx-ui-tooling"))
            }
        }

    }

}
