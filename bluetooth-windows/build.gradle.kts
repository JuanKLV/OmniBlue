plugins {
    id("java")
    kotlin("jvm")
}

group = "com.bluetooth.windows"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":bluetooth-core"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("net.java.dev.jna:jna:5.18.0")
    implementation("net.java.dev.jna:jna-platform:5.18.0")
}

tasks.test {
    useJUnitPlatform()
}