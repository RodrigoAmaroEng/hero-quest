plugins {
    kotlin("jvm") version "1.4.21"
    id("org.jetbrains.compose") version "0.2.0-build132"
}

repositories {
    jcenter()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    testImplementation 'junit:junit:4.13.1'
    testImplementation 'com.willowtreeapps.assertk:assertk-jvm:0.23'
    testImplementation 'org.apache.commons:commons-imaging:1.0-alpha2'
}

compose.desktop {
    application {
        mainClass = "MainKt"
    }
}


