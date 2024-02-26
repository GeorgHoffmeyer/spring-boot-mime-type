plugins {
    base
    java
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.springboot) apply false
    alias(libs.plugins.spring.dependency.management) apply false
    alias(libs.plugins.kotlin.plugin.spring) apply false
}

allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

tasks.wrapper {
    gradleVersion = "8.6"
    distributionType = Wrapper.DistributionType.BIN
}