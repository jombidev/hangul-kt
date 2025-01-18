/*
 * Set up build and verification targets, depending on what's enabled by the "targets" property.
 */

import org.gradle.api.GradleException
import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform
import org.gradle.nativeplatform.platform.internal.DefaultOperatingSystem
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet.Companion.COMMON_MAIN_SOURCE_SET_NAME
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
}

kotlin {
    val enabledTargets = (property("targets") as String).split(",")
    enabledTargets.forEach { enabledTarget ->
        when (enabledTarget) {
            "all" -> {
                configureCommonTargets()
                configureAppleTargets()
                configureWindowsTargets()
                configureLinuxTargets()
            }
            "common" -> {
                configureCommonTargets()
            }
            "native", "host" -> {
                val os: DefaultOperatingSystem = DefaultNativePlatform.getCurrentOperatingSystem()
                when {
                    os.isMacOsX -> configureAppleTargets(enabledTarget == "host")
                    os.isWindows -> configureWindowsTargets()
                    os.isLinux -> configureLinuxTargets()
                }
            }
            else -> {
                throw GradleException(
                    "Property 'targets' must be a comma-separated list of " +
                            "'all', 'native', 'common', or 'host'; found '$targets'"
                )
            }
        }
    }
}

fun KotlinMultiplatformExtension.configureCommonTargets() {
    jvm {
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }

    js(IR) {
        browser()
        nodejs()
    }
}

fun KotlinMultiplatformExtension.configureAppleTargets(hostOnly: Boolean = false) {
    val appleTargets = mutableListOf<KotlinNativeTarget>(
        macosX64(),
        macosArm64()
    )

    if (!hostOnly) {
        appleTargets.apply {
            add(iosX64())
            add(iosArm64())
            add(iosSimulatorArm64())

            add(watchosX64())
            add(watchosArm32())
            add(watchosArm64())
            add(watchosSimulatorArm64())

            add(tvosX64())
            add(tvosArm64())
            add(tvosSimulatorArm64())
        }
    }

    sourceSets {
        val appleMain by creating { dependsOn(getByName(COMMON_MAIN_SOURCE_SET_NAME)) }

        appleTargets.forEach { darwinTarget ->
            getByName("${darwinTarget.name}Main") { dependsOn(appleMain) }
        }
    }
}

fun KotlinMultiplatformExtension.configureWindowsTargets() {
    mingwX64()
}

fun KotlinMultiplatformExtension.configureLinuxTargets() {
    val linuxTargets = mutableListOf<KotlinNativeTarget>()

    val arch = System.getProperty("os.arch")
    if (arch == "amd64" || arch == "x86_64") {
        linuxTargets.add(linuxX64())
    }
    if (arch == "aarch64") {
        linuxTargets.add(linuxArm64())
    }

    linuxTargets.forEach { linuxTarget ->
        linuxTarget.compilations.getByName("main") {
            val uninorm by cinterops.creating
        }
    }

    sourceSets {
        val linuxMain by creating { dependsOn(getByName(COMMON_MAIN_SOURCE_SET_NAME)) }

        linuxTargets.forEach { linuxTarget ->
            getByName("${linuxTarget.name}Main") { dependsOn(linuxMain) }
        }
    }
}