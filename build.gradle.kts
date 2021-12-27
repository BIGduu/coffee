import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.Coroutines


plugins {
    kotlin("jvm").version("1.6.10")
    idea
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
}


subprojects {
    kotlin {
        experimental.coroutines = Coroutines.ENABLE
    }

    dependencies {
        implementation(kotlin("stdlib"))

        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "16"
        }
    }
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
