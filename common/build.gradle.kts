plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("de.mannodermaus.android-junit5")

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

    AndroidX.run {
        api(CORE_KTX)
        api(APPCOMPAT)
        api(CONSTRAINT_LAYOUT)
    }

    api(Google.MATERIAL)

    JUnit.run {
        testImplementation(JUPITER_API)
        testRuntimeOnly(JUPITER_ENGINE)
        testImplementation(ASSERTJ_CORE)
    }
}
