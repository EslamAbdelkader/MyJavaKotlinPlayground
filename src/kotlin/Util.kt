package kotlin

fun gcd(x: Int, y: Int): Int {
    var x = x
    if (y == 0) return x
    x %= y
    return gcd(y, x)
}