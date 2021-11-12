plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    applyDefault()

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    Network.run {
        api(RETROFIT)
        api(CONVERTER_MOSHI)
    }

    Moshi.run {
        api(KOTLIN)
        api(KOTLIN_CODEGEN)
        api(ADAPTERS)
    }

    api(Coroutines.CORE)

    Hilt.run {
        api(ANDROID)
        kapt(COMPILER)
    }

    AndroidX.run {
        api(CORE_KTX)
        api(APPCOMPAT)
        api(CONSTRAINT_LAYOUT)
    }

    api(Google.MATERIAL)
}
