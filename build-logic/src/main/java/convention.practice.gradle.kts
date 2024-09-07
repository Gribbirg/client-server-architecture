plugins {
    id("java")
    id("kotlin")
}

dependencies {
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}