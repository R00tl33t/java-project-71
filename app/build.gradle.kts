plugins {
    id("java")
    id("application")
    id("checkstyle")
    id("jacoco")
    id("org.sonarqube") version "6.0.1.5171"
}

application {
    mainClass.set("hexlet.code.App")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("info.picocli:picocli:4.7.6")
    annotationProcessor("info.picocli:picocli-codegen:4.7.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.2")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("org.skyscreamer:jsonassert:1.5.1")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}

tasks.named<JacocoReport>("jacocoTestReport") {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

checkstyle {
    configFile = file("${rootDir}/config/checkstyle/checkstyle.xml")
    toolVersion = '10.12.1'
    ignoreFailures = false
    showViolations = true
}

sonar {
    properties {
        property("sonar.projectKey", "R00tl33t_java-project-71")
        property("sonar.organization", "r00tl33t")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.login", System.getenv("SONAR_TOKEN") ?: "")
    }
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Aproject=${project.group}/${project.name}")
    options.release.set(17)
}

tasks.getByName<JavaExec>("run") {
    standardInput = System.`in`
}