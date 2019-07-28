fun main() {
    A().caller(Base())   // prints "Base.foo in A"
    B().caller(Base())  // prints "Base.foo in B" - dispatch receiver is resolved dynamically
    A().caller(Extended())  // prints "Base.foo in A" - extension receiver is resolved statically
    Base().foo()        // prints "Base.foo top-level"
}

open class Base
class Extended : Base()

open class A {
    open fun Base.foo() {
        println("Base.foo in A")
    }

    open fun Extended.foo() {
        println("Extended.foo in A")
    }

    fun caller(base: Base) {
        base.foo()
    }
}

class B : A() {
    override fun Base.foo() {
        println("Base.foo in B")
    }

    override fun Extended.foo() {
        println("Extended.foo in B")
    }
}

fun Base.foo() = println("Base.foo top-level")