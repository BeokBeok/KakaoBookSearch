plugins {
    id("com.android.application")
    id("kotlin-android")
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
}

dependencies {
    AndroidX.run {
        implementation(CORE_KTX)
        implementation(APPCOMPAT)
        implementation(CONSTRAINT_LAYOUT)
    }

    implementation(Google.MATERIAL)

    Navigation.run {
        implementation(FRAGMENT_KTX)
        implementation(UI_KTX)
    }
}
