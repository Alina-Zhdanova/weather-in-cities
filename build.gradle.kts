plugins {
	id("org.springframework.boot") version "4.0.0"
	id("io.spring.dependency-management") version "1.1.7"
	application
	id("org.openapi.generator") version "7.6.0"
}

group = "app"
version = "0.0.1-SNAPSHOT"
description = "A service for searching weather information in Russian cities"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

application {
	mainClass.set("app.weather_in_cities.WeatherInCitiesApplication")
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	implementation("org.springframework:spring-context")
	implementation("com.fasterxml.jackson.core:jackson-core")
	implementation("com.fasterxml.jackson.core:jackson-annotations")
	implementation("com.fasterxml.jackson.core:jackson-databind")
	implementation("com.fasterxml.jackson.jaxrs:jackson-jaxrs-json-provider")
	implementation("org.openapitools:jackson-databind-nullable:0.2.6")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
	implementation("jakarta.annotation:jakarta.annotation-api")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

openApiGenerate {
	generatorName.set("java")
	inputSpec.set(layout.projectDirectory.file("src/main/resources/OpenWeatherAPI.yaml").asFile.absolutePath)
	outputDir.set(layout.buildDirectory.dir("generated").get().asFile.absolutePath)
	apiPackage.set("app.weather_in_cities.api")
	invokerPackage.set("app.weather_in_cities.invoker")
	modelPackage.set("app.weather_in_cities.model")
	configOptions.set(
		mapOf(
			"library" to "restclient",
			"useJakartaEe" to "true",
			"useOneOfInterfaces" to "true",
			"serializableModel" to "true",
			"disallowAdditionalPropertiesIfNotPresent" to "true",
			"generateSupportingFiles" to "false",
			"generateApiTests" to "false",
			"generateModelTests" to "false"
	))
}

sourceSets {
	main {
		java {
			srcDir(layout.buildDirectory.dir("generated/src/main/java"))
		}
	}
}