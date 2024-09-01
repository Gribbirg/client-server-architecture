plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.bundles.plugins.gradle)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}