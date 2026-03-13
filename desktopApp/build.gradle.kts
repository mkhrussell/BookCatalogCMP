import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    dependencies {
        implementation(projects.shared)

        implementation(compose.desktop.currentOs)
        implementation(libs.compose.components.resources)
        implementation(libs.kotlinx.coroutinesSwing)

        implementation(libs.ktor.client.okhttp)
    }
}

compose.desktop {
    application {
        mainClass = "com.kamrul.bookcatalog.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.kamrul.bookcatalog"
            packageVersion = "1.0.0"

            windows {
                iconFile.set(project.file("src/main/resources/icons/windows-icon.ico"))
            }
            macOS {
                iconFile.set(project.file("src/main/resources/icons/macos-icon.icns"))
            }
            linux {
                iconFile.set(project.file("src/main/resources/icons/linux-icon.png"))
            }
        }
    }
}
