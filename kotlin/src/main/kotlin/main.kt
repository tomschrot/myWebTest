@file:Suppress("UNSUPPORTED", "WRONG_MODIFIER_TARGET", "UNRESOLVED_REFERENCE")

/**
 * https://devblogs.microsoft.com/visualstudio/announcing-visual-studio-code-java-installer/
 * https://developer.mozilla.org/de/docs/Web/JavaScript/Reference/Global_Objects/Date/toLocaleTimeString
 *
 * Simple example for Kotlin-JS on VS Code (Windows 10)
 * Build with Gradle
 * Debug in Chome browser
 * (c) 2019 Tom Schröter
 * tomschrot@gmail.com
 */

import kotlin.js.Date
import api.Interval

fun now(locale: String = "de-DE") =
        Date().toLocaleTimeString(locale)

object myPage: api.Page()

fun main() {

    println("Hello Kotlin")
    myPage.title = now("en-US")

    val iv = 
        Interval(2000){
            myPage.title = now("en-US")
        }.start()

    myPage.treat("_mainDIV") {
        innerText = "Hello Kotlin"
        addEventListener("click") { e: dynamic ->
            e.target.innerText = now()
            Unit
        }
    }

}