import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.Coroutines

plugins {
    kotlin("jvm").version("1.6.10")
    id("org.jetbrains.dokka").version("1.6.10")
    idea
}

buildscript {
    dependencies {
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.6.10")
    }
}

allprojects {
    group = "me.bigduu"
    version = "1.0-SNAPSHOT"
    apply {
        plugin("kotlin")
        plugin("idea")
    }
    repositories {
        mavenCentral()
    }
    dependencies {
        implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.6.10")
    }
}


subprojects {
    kotlin {
        experimental.coroutines = Coroutines.ENABLE
    }

    dependencies {
        // netty
        implementation("io.netty:netty-all:4.1.72.Final")

        // logback
        implementation("org.slf4j:slf4j-api:1.7.32")
        implementation("ch.qos.logback:logback-core:1.2.10")
        testImplementation("ch.qos.logback:logback-classic:1.2.10")

        // jackson
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
        implementation("com.fasterxml.jackson.core:jackson-databind:2.13.1")
        implementation("com.fasterxml.jackson.core:jackson-core:2.13.1")
        implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.1")

        // kotlin
        implementation(kotlin("stdlib"))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            incremental = true
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
