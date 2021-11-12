object AndroidX {
    const val CORE_KTX = "androidx.core:core-ktx:1.7.0"
    const val APPCOMPAT = "androidx.appcompat:appcompat:1.3.1"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.1"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:1.3.6"
    const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0"
}

object Google {
    const val MATERIAL = "com.google.android.material:material:1.4.0"
}

object Navigation {
    private const val VERSION = "2.3.5"

    const val FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:$VERSION"
    const val UI_KTX = "androidx.navigation:navigation-ui-ktx:$VERSION"
}

object Network {
    private const val RETROFIT_VERSION = "2.9.0"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
    const val CONVERTER_MOSHI = "com.squareup.retrofit2:converter-moshi:$RETROFIT_VERSION"
}

object JUnit {
    private const val VERSION = "5.8.1"

    const val JUPITER_API = "org.junit.jupiter:junit-jupiter-api:$VERSION"
    const val JUPITER_ENGINE = "org.junit.jupiter:junit-jupiter-engine:$VERSION"

    const val ASSERTJ_CORE = "org.assertj:assertj-core:3.21.0"
}

object Mock {
    const val WEB_SERVER = "com.squareup.okhttp3:mockwebserver:4.9.2"
}

object Moshi {
    private const val VERSION = "1.12.0"

    const val KOTLIN = "com.squareup.moshi:moshi-kotlin:$VERSION"
    const val ADAPTERS = "com.squareup.moshi:moshi-adapters:$VERSION"
    const val KOTLIN_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:$VERSION"
}

object Coroutines {
    const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2"
}

object Hilt {
    const val VERSION = "2.40"

    const val ANDROID = "com.google.dagger:hilt-android:$VERSION"
    const val COMPILER = "com.google.dagger:hilt-compiler:$VERSION"
}
