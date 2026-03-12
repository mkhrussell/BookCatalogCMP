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
        }
    }
}
