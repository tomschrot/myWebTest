@file:Suppress("UNSUPPORTED", "WRONG_MODIFIER_TARGET", "UNRESOLVED_REFERENCE", "UNCHECKED_CAST")

package api

import org.w3c.dom.*

private external val window  : Window   = definedExternally
private external val document: Document = definedExternally

private typealias anyFunc = () -> Unit

class Interval() {
    private var _id = 0

    var delay : Int      = 1000
    var action: anyFunc? = null

    constructor(self: Interval.() -> Unit): this() {
        self(this)
    }

    constructor(delay: Int, action: anyFunc): this() {
        this.delay  = delay
        this.action = action
    }

    fun start(): Interval {
        if(_id == 0)
            _id = window.setInterval(action, delay)
        return this
    }

    infix fun start(action: anyFunc): Interval {
        if(_id == 0)
            this.action = action
        return start()
    }

    fun stop() {
        if(_id != 0)
            window.clearInterval(_id)
        _id = 0
    }
}