plugins {
    kotlin("multiplatform")// version "2.1.0"
    id("cinterop")
    id("targets")

    `maven-publish`
}

kotlin {
    explicitApi()
    
    sourceSets {
        all {
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
        }
        
        val commonTest by getting {
            dependencies { 
                implementation(kotlin("test"))
            }
        }
    }
}

group = "dev.jombi"
version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(8)
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

publishing {
    publications.withType<MavenPublication> {
        val pomDescription = "KMP Port of es-hangul library"
        val pomUrl = "https://github.com/jombidev/hangul-kt"
        val pomLicenseName: String by project
        val pomLicenseUrl: String by project
        val pomScmUrl: String by project
        val pomScmConnection: String by project
        val pomScmDeveloperConnection: String by project
        val pomDeveloperId: String by project
        val pomDeveloperName: String by project

        // Publish docs with each artifact.
        artifact(javadocJar)

        // Provide information requited by Maven Central.
        pom {
            name.set(rootProject.name)
            description.set(pomDescription)
            url.set(pomUrl)

            licenses {
                license {
                    name.set(pomLicenseName)
                    url.set(pomLicenseUrl)
                }
            }

            scm {
                url.set(pomScmUrl)
                connection.set(pomScmConnection)
                developerConnection.set(pomScmDeveloperConnection)
            }

            developers {
                developer {
                    id.set(pomDeveloperId)
                    name.set(pomDeveloperName)
                }
            }
        }
    }
}