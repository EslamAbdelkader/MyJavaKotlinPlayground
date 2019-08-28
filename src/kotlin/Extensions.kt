package kotlin

fun main() {
    A().caller(Base())   // prints "kotlin.Base.kotlin.foo in kotlin.A"
    B().caller(Base())  // prints "kotlin.Base.kotlin.foo in kotlin.B" - dispatch receiver is resolved dynamically
    A().caller(Extended())  // prints "kotlin.Base.kotlin.foo in kotlin.A" - extension receiver is resolved statically
    Base().foo()        // prints "kotlin.Base.kotlin.foo top-level"
}

open class Base
class Extended : Base()

open class A {
    open fun Base.foo() {
        println("kotlin.Base.kotlin.foo in kotlin.A")
    }

    open fun Extended.foo() {
        println("kotlin.Extended.kotlin.foo in kotlin.A")
    }

    fun caller(base: Base) {
        base.foo()
    }
}

class B : A() {
    override fun Base.foo() {
        println("kotlin.Base.kotlin.foo in kotlin.B")
    }

    override fun Extended.foo() {
        println("kotlin.Extended.kotlin.foo in kotlin.B")
    }
}

fun Base.foo() = println("kotlin.Base.kotlin.foo top-level")