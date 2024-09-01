plugins {
    id("java")
    id("kotlin")
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}