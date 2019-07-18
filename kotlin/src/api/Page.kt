@file:Suppress("UNSUPPORTED", "WRONG_MODIFIER_TARGET", "UNRESOLVED_REFERENCE", "UNCHECKED_CAST")

package api

import org.w3c.dom.Document
import org.w3c.dom.Window
import org.w3c.dom.Element
import org.w3c.dom.events.Event

private external val window  : Window   = definedExternally
private external val document: Document = definedExternally

infix fun String.of(p: Page) = p[this]

open class Page {

    var title: String
        get() = document.title
        set(value) { document.title = value}

    operator fun get(name: String): dynamic =
            document.getElementById(name)

    operator fun <T> get(name: String): T =
            document.getElementById(name) as T

    fun treat(name: String, config: dynamic.() -> Unit) =
        config(this[name])

}