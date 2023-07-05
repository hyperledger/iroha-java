import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.22"
    application
}

group = "jp.co.soramitsu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    val iroha2Ver by System.getProperties()

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.7.2")

    api("com.github.hyperledger.iroha-java:admin-client:$iroha2Ver")
    implementation("com.github.hyperledger.iroha-java:model:$iroha2Ver")
    implementation("com.github.hyperledger.iroha-java:block:$iroha2Ver")

    implementation("net.i2p.crypto:eddsa:0.3.0")
    implementation("org.bouncycastle:bcprov-jdk15on:1.70")
    implementation("com.github.multiformats:java-multihash:1.3.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKt")
}
