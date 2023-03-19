import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.4"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
}

group = "com.anloboda"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	implementation("org.telegram:telegrambots-spring-boot-starter:6.5.0")

	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.5")
	implementation("io.github.openfeign:feign-okhttp:10.12")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")

	implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")

	implementation("org.slf4j:slf4j-api:1.7.32")
	implementation("ch.qos.logback:logback-classic:1.2.5")

	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("com.github.ben-manes.caffeine:caffeine:3.0.4")

	implementation("io.github.resilience4j:resilience4j-spring-boot2:2.0.2")
	implementation("org.springframework.boot:spring-boot-starter-aop:3.0.4")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.mockk:mockk:1.13.4")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	testLogging.events("PASSED", "FAILED", "SKIPPED")
}