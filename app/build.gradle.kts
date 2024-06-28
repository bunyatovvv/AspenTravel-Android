plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.behmennnn.aspentravel"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.behmennnn.aspentravel"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.swiperefreshlayout)
    val nav_version = "2.7.7"
    val retrofit_version = "2.11.0"
    val okhttp_version = "4.12.0"
    val dagger_version = "2.51.1"

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    ///Retrofit
    implementation(libs.retrofit)
    implementation (libs.converter.gson)

    //OkHttp
    implementation("com.squareup.okhttp3:okhttp:$okhttp_version")
    implementation("com.squareup.okhttp3:okhttp:$okhttp_version")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttp_version")

    //Dagger
    implementation ("com.google.dagger:hilt-android:$dagger_version")
    kapt ("com.google.dagger:hilt-compiler:$dagger_version")
    annotationProcessor ("com.google.dagger:hilt-compiler:$dagger_version")

    //Lifecycle
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    //RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.15.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.0")

    //Viewpager
    implementation("androidx.viewpager2:viewpager2:1.1.0")

    //Google Maps
    implementation("com.google.android.gms:play-services-maps:19.0.0")



}