plugins {
    id("java-library")
    kotlin("jvm")
    id("buildsrc.convention.kotlin-jvm")
}

group = "com.bluetooth.core"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}



