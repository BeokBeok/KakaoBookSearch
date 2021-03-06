plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("de.mannodermaus.android-junit5")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    applyDefault()

    defaultConfig {
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    Network.run {
        implementation(RETROFIT)
        implementation(CONVERTER_MOSHI)
    }

    Moshi.run {
        implementation(KOTLIN)
        implementation(KOTLIN_CODEGEN)
        implementation(ADAPTERS)
    }

    implementation(Coroutines.CORE)

    implementation(Google.MATERIAL)

    AndroidX.run {
        implementation(CORE_KTX)
        implementation(APPCOMPAT)
        implementation(CONSTRAINT_LAYOUT)
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
