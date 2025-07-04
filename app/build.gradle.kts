import java.util.Properties

plugins {
    kotlin("kapt")
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetpack.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dagger.hilt)
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile>().configureEach {
    compilerOptions {
        allWarningsAsErrors.set(true)
        freeCompilerArgs.add("-Xsuppress-version-warnings")
    }
}
android {
    namespace = "com.lmorda.homework"
    compileSdk = 35
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        applicationId = "com.lmorda.homework"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        val localProperties = Properties().apply {
            load(rootProject.file("homework.properties").inputStream())
        }
        buildConfigField("String", "ACCOUNT_TOKEN", "\"${localProperties["ACCOUNT_TOKEN"]}\"")
        buildConfigField("String", "API_KEY", "\"${localProperties["API_KEY"]}\"")
        val mapsApiKey = localProperties.getProperty("MAPS_API_KEY")
        manifestPlaceholders["MAPS_API_KEY"] = mapsApiKey
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        compose = true
    }
    // TODO: Add release variant
}

dependencies {

    // AndroidX Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.lifecycle)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.runtime.livedata)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android.gradle.plugin)
    implementation(libs.androidx.hilt.navigation.compose)

    // Retrofit + OkHttp + Serialization
    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.kotlinx.serialization.json)

    // Timber + Lottie + Coil
    implementation(libs.timber)
    implementation(libs.lottie)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // Google Maps
    implementation(libs.maps.compose)
    implementation(libs.play.services.maps)

    // Unit Testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.mockk)
    testImplementation(libs.mockk.android)
    testImplementation(libs.turbine)
    testImplementation(libs.livedata.testing)

    // Screenshot testing
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
}
