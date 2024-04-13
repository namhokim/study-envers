plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.data:spring-data-envers")
	implementation("io.github.wimdeblauwe:error-handling-spring-boot-starter:4.2.0")
	implementation("io.swagger.core.v3:swagger-core-jakarta:2.2.20")    // 2.2.19 has ["XXX PROPS " issue](https://github.com/springdoc/springdoc-openapi/issues/2486)
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.3.0") {
		exclude(group = "io.swagger.core.v3", module = "swagger-core-jakarta")
	}
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
	implementation("de.pentabyte:springfox-enum-plugin:3.0.0")
	runtimeOnly("com.h2database:h2")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
