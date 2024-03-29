import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
	id("info.solidsoft.pitest") version "1.15.0"
	kotlin("jvm") version "1.9.20"
	kotlin("plugin.spring") version "1.9.20"
	jacoco
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.willowtreeapps.assertk:assertk:0.27.0")
	testImplementation("info.solidsoft.gradle.pitest:gradle-pitest-plugin:1.15.0")
	testImplementation("io.mockk:mockk:1.13.8")
	testImplementation("net.jqwik:jqwik:1.8.2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
	finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
	reports {
		xml.required = true
		csv.required = false
	}
}

pitest {
	targetClasses.add("com.example.weebLib.*")
	junit5PluginVersion = "1.2.0"
	avoidCallsTo.set(setOf("kotlin.jvm.internal"))
	mutators.set(setOf("STRONGER"))
	threads.set(Runtime.getRuntime().availableProcessors())
	testSourceSets.addAll(sourceSets["test"])
	mainSourceSets.addAll(sourceSets["main"])
	outputFormats.addAll("XML", "HTML")
	excludedClasses.add("**WeebLibApplication")
}
