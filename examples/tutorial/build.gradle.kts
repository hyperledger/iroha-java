dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.0")

    implementation("net.i2p.crypto:eddsa:0.3.0")
    implementation("org.bouncycastle:bcprov-jdk15on:1.65")
    implementation("com.github.multiformats:java-multihash:1.3.0")

    implementation(project(":model"))
    implementation(project(":block"))
    api(project(":admin-client"))
}

tasks.testCoverage {
    mustRunAfter(":admin-client:testCoverage")
    mustRunAfter(":block:testCoverage")
    mustRunAfter(":client:testCoverage")
    mustRunAfter(":codegen:testCoverage")
    mustRunAfter(":model:testCoverage")
    mustRunAfter(":test-tools:testCoverage")
}
