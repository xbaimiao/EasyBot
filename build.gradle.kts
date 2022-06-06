plugins {
    java
    id("io.izzel.taboolib") version "1.39"
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
	`maven-publish`
}

taboolib {
    description {
        contributors {
            name("小白").description("TabooLib Developer")
        }
//        dependencies {
//            name("ExamplePlugin")
//            name("ExamplePlugin").optional(true)
//        }
    }
    install("common")
    install("common-5")
    install("platform-bukkit")
    install("platform-bungee")
    install("module-configuration")
    install("module-database")
    install("module-chat")
	install("module-lang")
	install("expansion-command-helper")
    version = "6.0.7-64"
}

repositories {
    mavenCentral()
	maven(url = uri("https://run.xbaimiao.com/releases/"))
}

dependencies {
    compileOnly("ink.ptms.core:v11701:11701:mapped")
    compileOnly("ink.ptms.core:v11701:11701:universal")
    compileOnly("public:waterfall:1.0.0")
//    taboo("com.xbaimiao:mirai-http-sdk:1.0.3-alpha-dev-4-mini")
    implementation("com.xbaimiao:mirai-http-sdk:1.0.3")
    compileOnly(kotlin("stdlib"))
    compileOnly(fileTree("libs"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "11"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

publishing {
    repositories {
        maven("https://run.xbaimiao.com/releases/") {
            credentials {
                username = project.findProperty("user").toString()
                password = project.findProperty("password").toString()
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            groupId = "com.xbaimiao"
        }
    }
}