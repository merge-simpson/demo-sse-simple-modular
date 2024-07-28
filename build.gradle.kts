plugins {
    java
    `java-library`
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

val commonProject = project(":common")

val sseCoreModule: String by project

val inquiryServiceModule: String by project
val demoSimpleSseServiceModule: String by project

val inquiryApiModule: String by project
val inquiryDomainModule: String by project
val inquiryExceptionModule: String by project
val inquiryReadModelModule: String by project

subprojects {
    group = "me.letsdev"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
        }
    }

    repositories {
        mavenCentral()
        google()
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
        configureEach {
            // exclude logback
            exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
        }
    }

    dependencies {
        implementation("org.springframework:spring-web")
        implementation("org.springframework:spring-context")
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-log4j2")
        implementation("com.fasterxml.jackson.core:jackson-annotations")
        developmentOnly("org.springframework.boot:spring-boot-devtools")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

        // Pageable
        implementation("org.springframework.data:spring-data-commons")

        // Lombok
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        if (project.name != commonProject.name) {
            api(commonProject)
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<JavaCompile> {
        val hasMapstructDependency = project
                .configurations
                .findByName("implementation")
                ?.dependencies
                ?.any { dep ->
                    dep.group == "org.mapstruct" && dep.name == "mapstruct"
                } ?: false

        val jvmArgsList = mutableListOf<String>().apply {
            addAll(listOf(
                    "--enable-preview",
            ))
            if (hasMapstructDependency) {
                add("-Amapstruct.defaultComponentModel=spring")
            }
        }

        options.compilerArgs.addAll(jvmArgsList)
        println("options.forkOptions.jvmArgs=${options.compilerArgs}")
    }
}

// common module ───────────────────────────────────────────────────────────────────────────────────────────────────────
project(":common") {

}

// core module ─────────────────────────────────────────────────────────────────────────────────────────────────────────
project(sseCoreModule) {
    dependencies {
        implementation("org.springframework:spring-webmvc")
    }
}

// service module ──────────────────────────────────────────────────────────────────────────────────────────────────────
project(inquiryServiceModule) {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation(project(inquiryApiModule))
    }
}

project(demoSimpleSseServiceModule) {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation(project(inquiryApiModule))
    }
}

// internal module ─────────────────────────────────────────────────────────────────────────────────────────────────────
//  inquiry
//  └── internal
project(inquiryApiModule) {
    dependencies {
        api(project(inquiryDomainModule))
        api(project(inquiryExceptionModule))
        api(project(inquiryReadModelModule))
    }
}

project(inquiryReadModelModule) {
    dependencies {
        api(project(inquiryDomainModule))
    }
}

// external module ─────────────────────────────────────────────────────────────────────────────────────────────────────
//  inquiry
//  └── external