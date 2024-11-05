import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.ssuclass.instagramstorysharingsample"
    compileSdk = 34

    // `local.properties` 파일에서 값을 읽어옴
    val localProperties = Properties()
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use { localProperties.load(it) }
    }

    defaultConfig {
        applicationId = "com.ssuclass.instagramstorysharingsample"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // resValue를 통해 XML 리소스에서 사용할 수 있도록 설정
        resValue("string", "facebook_app_id", localProperties["facebook_app_id"] as String? ?: "")
        resValue(
            "string",
            "facebook_client_token",
            localProperties["facebook_client_id"] as String? ?: ""
        )
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("com.facebook.android:facebook-android-sdk:latest.release")
}