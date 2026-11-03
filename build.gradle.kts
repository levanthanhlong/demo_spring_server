plugins {
	java
	id("org.springframework.boot") version "3.5.16"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
	maven("https://central.sonatype.com/repository/maven-snapshots/")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	val cshieldSdkJar = findProperty("cshieldSdkJar")
	if (cshieldSdkJar != null) {
		implementation(files(cshieldSdkJar))
		implementation("org.bouncycastle:bcprov-jdk18on:1.78")
	} else {
		implementation("io.github.levanthanhlong:cshield-spring-sdk:1.0.0-SNAPSHOT")
	}
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootJar {
	enabled = true
}

tasks.jar {
	enabled = false
}
