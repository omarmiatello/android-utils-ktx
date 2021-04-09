import org.jetbrains.kotlin.konan.properties.loadProperties

buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha14")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

// Project configuration

class Module(val name: String, val description: String, val group: String, val version: String)

val projectName = "android-utils-ktx"
val projectVersion = "1.0.0"
val projectGroup = "com.github.omarmiatello.android-utils-ktx"
val projectRepoPath = "github.com/omarmiatello/android-utils-ktx"
val sharedArtifacts = listOf(
    Module(
        name = "easyfrc",
        description = "Simple configuration: Variables for Firebase Remote Config",
        group = projectGroup,
        version = "1.1.0"
    ),
    Module(
        name = "commonintent",
        description = "Common intents for Android",
        group = projectGroup,
        version = "1.1.0"
    )
)

// Plugins configuration

plugins {
    id("io.github.gradle-nexus.publish-plugin") version "1.0.0"
}

// mavenCentral() configuration

group = projectGroup
version = projectVersion

loadProperties(file("local.properties").absolutePath).also { p ->
    p.stringPropertyNames().forEach { key ->
        ext[key] = p.getProperty(key)
    }
}
val ossrhUsername: String? by ext
val ossrhPassword: String? by ext

nexusPublishing {
    repositories {
        create("myNexus") {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            username.set(ossrhUsername)
            password.set(ossrhPassword)
        }
    }
}

val sharedArtifactsMap = sharedArtifacts.associateBy { ":${it.name}" }
configure(subprojects.filter { it.path in sharedArtifactsMap.keys }) {
    //apply<JavaLibraryPlugin>()
    apply<SigningPlugin>()
    apply<MavenPublishPlugin>()

//    configure<JavaPluginExtension> {
//        withJavadocJar()
//        withSourcesJar()
//    }

    configure<SigningExtension> {
        val publishing: PublishingExtension by project
        sign(publishing.publications)
    }

    configure<PublishingExtension> {
        publications {
            val main by creating(MavenPublication::class) {
//                from(components["java"])

                pom {
                    val module = sharedArtifactsMap.getValue(project.path)
                    name.set("$projectName-${project.name}")
                    description.set(module.description)
                    url.set("https://$projectRepoPath")
                    group = module.group
                    version = module.version
                    licenses {
                        license {
                            name.set("MIT License")
                            // https://opensource.org/licenses/MIT
                            url.set("https://$projectRepoPath/blob/master/LICENSE")
                        }
                    }
                    developers {
                        developer {
                            id.set("jackl85")
                            name.set("Omar Miatello")
                            email.set("omar.miatello@gmail.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:$projectRepoPath.git")
                        developerConnection.set("scm:git:ssh://$projectRepoPath.git")
                        url.set("https://$projectRepoPath/tree/master")
                    }
                }
            }
        }
        repositories {
            maven {
                name = project.name
                setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = ossrhUsername
                    password = ossrhPassword
                }
            }
        }
    }
}
