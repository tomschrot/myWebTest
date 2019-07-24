@file:Suppress("UNSUPPORTED", "WRONG_MODIFIER_TARGET", "UNRESOLVED_REFERENCE", "UNCHECKED_CAST")

package api

import org.w3c.dom.*

private external val window  : Window = definedExternally

private typealias anyFunc = () -> Unit
/**
 * Exposes window. setInterval/clearInterval to Kotlin
 *
 * @see https://developer.mozilla.org/en-US/docs/Web/API/WindowOrWorkerGlobalScope/setInterval
 */
class Interval() {
    private var _id = 0

    var delay : Int      = 1000
    var action: anyFunc? = null

    constructor(self: Interval.() -> Unit): this() {
        self(this)
    }

    constructor(delay: Int, action: anyFunc): this() {
        start(delay, action)
    }

    fun start(): Interval {
        stop()
        _id = window.setInterval(action, delay)
        return this
    }

    fun start(delay: Int = 1000, action: anyFunc): Interval {
        this.delay  = delay
        this.action = action
        return start()
    }

    fun stop() {
        window.clearInterval(_id)
        _id = 0
    }
}