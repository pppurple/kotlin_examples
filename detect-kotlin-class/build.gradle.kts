import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.21"
}

group = "com.example.kotlin.detect"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.21")
}

/*
tasks.test {
    useJUnitPlatform()
}
*/

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}
