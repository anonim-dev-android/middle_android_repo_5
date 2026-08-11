plugins {
    alias(libs.plugins.library.convention)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.yandex.practicum.middle_homework_5.news"
}

dependencies {
    implementation(libs.androidx.paging.compose)
    implementation(libs.goodle.gson)
    implementation(libs.bundles.room)
    kapt(libs.room.kapt)
    implementation(libs.squareup.retrofit2)
    implementation(libs.androidx.work.manager.ktx)

    implementation(project(":settings"))
}