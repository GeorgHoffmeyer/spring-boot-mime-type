plugins {
    id("base")
    id(libs.plugins.kotlin.jvm.get().pluginId)
    id(libs.plugins.springboot.get().pluginId)
    id(libs.plugins.spring.dependency.management.get().pluginId)
    id(libs.plugins.kotlin.plugin.spring.get().pluginId)
}


dependencies {
    implementation(libs.spring.boot.starter.web)
    testImplementation(libs.spring.boot.starter.test) {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        exclude(group = "junit", module = "junit")
        exclude(module = "mockito-core")
    }
}