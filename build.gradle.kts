import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("multiplatform") version "2.1.0"

    id("com.vanniktech.maven.publish") version "0.30.0"
}

kotlin {
    explicitApi()
    
    jvm() 
    
    mingwX64()
    
    watchosX64()
    watchosArm32()
    watchosArm64()
    watchosSimulatorArm64()
    
    tvosX64()
    tvosArm64()
    tvosSimulatorArm64()
    
    macosX64()
    macosArm64()
    
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
    linuxX64()
    linuxArm64()
    
    sourceSets {
        commonMain.dependencies {
            implementation("com.ionspin.kotlin:bignum:0.3.10")
            implementation("com.doist.x:normalize:1.1.1")
        }
        
        commonTest.dependencies { 
            implementation(kotlin("test"))
        }
    }
}

group = "dev.jombi"
version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}


mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
    coordinates(group.toString(), rootProject.name, version.toString())
    
    pom {
        name = rootProject.name
        description = "A Kotlin Ported-library for handling Hangul characters."
        url = "https://github.com/jombidev/hangul-kt"
        
        licenses {
            license {
                name = "MIT"
                url = "https://opensource.org/licenses/MIT"
            }
        }
        
        developers { 
            developer {
                id = "jombidev"
                name = "Jombi"
                url = "https://jombi.dev"
            }
        }
        
        scm {
            url = "https://github.com/jombidev/hangul-kt"
            connection = "scm:git:git://github.com/jombidev/hangul-kt.git"
            developerConnection = "scm:git:ssh://git@github.com/jombidev/hangul-kt.git"
        }
    }
}
