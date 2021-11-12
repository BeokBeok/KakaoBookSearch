plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("de.mannodermaus.android-junit5")
    id("dagger.hilt.android.plugin")
}

android {
    applyDefault()

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":common"))

    AndroidX.run {
        implementation(FRAGMENT_KTX)
        implementation(LIFECYCLE_RUNTIME_KTX)
        testImplementation(CORE_TESTING)
    }

    Hilt.run {
        implementation(ANDROID)
        kapt(COMPILER)
    }

    Glide.run {
        implementation(CORE)
        kapt(COMPILER)
    }

    JUnit.run {
        testImplementation(JUPITER_API)
        testRuntimeOnly(JUPITER_ENGINE)
        testImplementation(ASSERTJ_CORE)
    }

    Mock.run {
        testImplementation(WEB_SERVER)
        testImplementation(K)
    }
}
