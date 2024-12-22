plugins {
    alias(libs.plugins.astracrypt.android.library)
}

android {
    namespace = "com.nevidimka655.haptic"
}

dependencies {
    api(libs.androidx.core.ktx)
}