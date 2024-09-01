plugins {
    id("java")
    id("kotlin")
}

tasks.test {
    useJUnitPlatform()
}