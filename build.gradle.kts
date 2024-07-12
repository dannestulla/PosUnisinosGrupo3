plugins {
	java
	id("org.springframework.boot") version "3.3.1"
	id("io.spring.dependency-management") version "1.1.5"
}

group = "br.unisinos"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(22)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register("stage") {
	dependsOn("build", "clean")
}

tasks.named("build").configure {
	mustRunAfter(tasks.named("clean"))
}

tasks.register<Copy>("copyToLib") {
	into("$buildDir/libs")
	from(configurations.getByName("compileClasspath"))
}

tasks.named("stage").configure {
	dependsOn(tasks.named("copyToLib"))
}

