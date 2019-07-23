
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinJsDce

val outDir = "$projectDir\\out"

plugins {
    id ("kotlin2js"    ) version "1.3.41"
    id ("kotlin-dce-js") version "1.3.41"
}

dependencies {
    compile(kotlin("stdlib-js"))
}

repositories {
    jcenter()
    mavenCentral()
}

sourceSets {
    main {
        java.srcDir("src")
    }
}


tasks.register("copystdlib") {
    group       = "Helpers"
    description = "copies js stdlib"

    inputs.property("compileClasspath", configurations.compileClasspath.get())

    doLast {
        val kotlinStdLibJar = configurations.compileClasspath.get().single {
            it.name.matches(Regex("kotlin-stdlib-js-.+\\.jar"))
        }
        copy {
            includeEmptyDirs = false
            from(zipTree(kotlinStdLibJar))
            into("$projectDir\\lib")
            include("**/*.js")
            exclude("META-INF/**")
        }
    }
}

tasks {

    compileKotlin2Js {
       
        dependsOn("copystdlib")
 
		kotlinOptions {
			outputFile          = "$projectDir\\out\\app.js"
            suppressWarnings    = true
	        main                = "call" // call, noCall
	        metaInfo            = true
	        moduleKind          = "plain" // plain, amd, commonjs, umd
	        noStdlib            = true

            sourceMap             = true
            sourceMapEmbedSources = "always" // always, never, inlining

	        target                = "v5"
	        typedArrays           = true

            freeCompilerArgs      = listOf("-output-prefix", "$projectDir\\lib\\kotlin.js")
        }
    }
    
    runDceKotlinJs {
        dependsOn(compileKotlin2Js)
        dceOptions.outputDirectory = "../js"
    }

}