import org.gradle.api.artifacts.dsl.DependencyHandler

object Version {
    const val min_sdk = 21
    const val max_sdk = 32

    //Common
    const val androidx_appcompat = "1.4.1"
    const val android_material = "1.5.0"
    const val androidx_constraint = "2.1.3"
    const val genericAdapter = "1.6.2"

    //Ktx
    const val ktx_androidx_core = "1.7.0"
    const val ktx_androidx_activity = "1.4.0"
    const val ktx_androidx_fragment = "1.4.1"
    const val ktx_a_reflection = "1.6.10"

    //Hilt
    const val hilt = "2.38.1"
    const val hilt_androidx_compiler = "1.0.0"

    //Networking
    const val retrofit = "2.9.0"
    const val okhttp = "4.9.2"
    const val network_checker = "1.0.3"

    //Picasso
    const val picasso = "2.8"
    const val transformation = "2.3.0"

    //Google
    const val google_places = "2.5.0"
    const val google_play_services = "18.0.0"

    //Room
    const val roomVersion = "2.4.2"

    //Test
    const val junit_test = "4.13.2"
    const val androidx_test_ext = "1.1.3"
    const val androidx_test_espresso = "3.4.0"
}

object Dependencies {
    //Common
    const val ax_appcompat = "androidx.appcompat:appcompat:${Version.androidx_appcompat}"
    const val a_material = "com.google.android.material:material:${Version.android_material}"
    const val ax_constraint =
        "androidx.constraintlayout:constraintlayout:${Version.androidx_constraint}"
    const val a_generic_adapter =
        "com.github.ivankostadinovic:GenericAdapter:${Version.genericAdapter}"

    //Ktx
    const val ktx_ax_core = "androidx.core:core-ktx:${Version.ktx_androidx_core}"
    const val ktx_ax_activity = "androidx.activity:activity-ktx:${Version.ktx_androidx_activity}"
    const val ktx_ax_fragment = "androidx.fragment:fragment-ktx:${Version.ktx_androidx_fragment}"
    const val ktx_reflection = "org.jetbrains.kotlin:kotlin-reflect:${Version.ktx_a_reflection}"

    //Hilt
    const val ax_hilt = "com.google.dagger:hilt-android:${Version.hilt}"
    const val ax_hilt_compiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
    const val hilt_androidx_compiler =
        "androidx.hilt:hilt-compiler:${Version.hilt_androidx_compiler}"

    //Networking
    const val retrofit_retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val retrofit_converter_gson = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
    const val okhttp_okhttp = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
    const val okhttp_logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
    const val network_check = "com.github.vladan29:internet_checker:${Version.network_checker}"

    //Picasso
    const val a_picasso = "com.squareup.picasso:picasso:${Version.picasso}"
    const val a_transformation = "com.makeramen:roundedimageview:${Version.transformation}"

    //Google
    const val a_google_places =
        "com.google.android.libraries.places:places:${Version.google_places}"
    const val a_google_olay_services =
        "com.google.android.gms:play-services-basement:${Version.google_play_services}"

    //Room
    const val room_base = "androidx.room:room-runtime:${Version.roomVersion}"
    const val room_kapt = "androidx.room:room-compiler:${Version.roomVersion}"
    const val room_ktx = "androidx.room:room-ktx:${Version.roomVersion}"

    //Test
    const val test_junit = "junit:junit:${Version.junit_test}"
    const val ax_test_ext = "androidx.test.ext:junit:${Version.androidx_test_ext}"
    const val ax_test_espresso =
        "androidx.test.espresso:espresso-core:${Version.androidx_test_espresso}"

}

fun DependencyHandler.common() {
    implementation(Dependencies.ax_appcompat)
    implementation(Dependencies.a_material)
    implementation(Dependencies.ax_constraint)
    implementation(Dependencies.a_generic_adapter)
}

fun DependencyHandler.ktx() {
    implementation(Dependencies.ktx_ax_core)
    implementation(Dependencies.ktx_ax_activity)
    implementation(Dependencies.ktx_ax_fragment)
    implementation(Dependencies.ktx_reflection)
}

fun DependencyHandler.networking() {
    implementation(Dependencies.retrofit_retrofit)
    implementation(Dependencies.retrofit_converter_gson)
    implementation(Dependencies.okhttp_okhttp)
    implementation(Dependencies.okhttp_logging_interceptor)
    implementation(Dependencies.network_check)

}

fun DependencyHandler.hilt() {
    implementation(Dependencies.ax_hilt)
    kapt(Dependencies.ax_hilt_compiler)
    kapt(Dependencies.hilt_androidx_compiler)
}

fun DependencyHandler.picasso() {
    implementation(Dependencies.a_picasso)
    implementation(Dependencies.a_transformation)
}

fun DependencyHandler.googlePlaces() {
    implementation(Dependencies.a_google_places)
    implementation(Dependencies.a_google_olay_services)
}

fun DependencyHandler.room() {
    kapt(Dependencies.room_kapt)
    implementation(Dependencies.room_base)
    implementation(Dependencies.room_ktx)
}

fun DependencyHandler.test() {
    androidTestImplementation(Dependencies.test_junit)
    androidTestImplementation(Dependencies.ax_test_ext)
    androidTestImplementation(Dependencies.ax_test_espresso)
}

// Some helper extensions --------------------------------------------------------------------------
private fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}

private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}

private fun DependencyHandler.kaptAndroidTest(depName: String) {
    add("kaptAndroidTest", depName)
}

private fun DependencyHandler.api(depName: String) {
    add("api", depName)
}

private fun DependencyHandler.androidTestImplementation(depName: String) {
    add("androidTestImplementation", depName)
}

private fun DependencyHandler.bom(depName: Any) {
    add("implementation", depName)
}