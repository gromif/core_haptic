plugins {
    alias(libs.plugins.astracrypt.android.library)
}

android {
    namespace = "com.nevidimka655.haptic"
}

dependencies {
    implementation(libs.androidx.core.ktx)
}