package kotlin

import java.util.*
import kotlin.math.max

fun main() {
    val scan = Scanner(System.`in`)
    val size = scan.nextInt()
    val list:Iterable<Char> = scan.next().asIterable()
    pangram(list)
}

//  http://codeforces.com/contest/731/problem/A
fun diceRoll(first: Int, second: Int): String {
    val max = max(first, second)
    val prob = 6 - (max - 1)
    val gcd = gcd(prob, 6)
    return "${prob / gcd}/${6 / gcd}"
}

//  http://codeforces.com/contest/490/problem/A
fun teamOlympiad(list: List<Int>) {
    val ones = mutableListOf<Int>()
    val twos = mutableListOf<Int>()
    val threes = mutableListOf<Int>()
    for (i in 0 until list.size) {
        when (list[i]) {
            1 -> ones.add(i + 1)
            2 -> twos.add(i + 1)
            3 -> threes.add(i + 1)
        }
    }
    val teamsCount = minOf(ones.size, twos.size, threes.size)
    println(teamsCount)
    for (i in 0 until teamsCount) {
        print("${ones[i]} ${twos[i]} ${threes[i]}\n")
    }


}

//  http://codeforces.com/problemset/problem/767/A
fun snackTower(list: MutableList<Int>) {
    var max = list.size
    val queue = PriorityQueue<Int> { o1, o2 -> o2.compareTo(o1) }
    for (i in list) {
        queue.offer(i)
        while (queue.isNotEmpty() && queue.peek() == max) {
            print("${queue.poll()} ")
            max--
        }
        println()
    }
}

//  http://codeforces.com/contest/520/problem/A
fun pangram(list: Iterable<Char>) {
    val set = mutableSetOf<Char>()
    for (c in list) {
        set.add(c.toLowerCase())
    }
    val result = if (set.size == 26) "YES" else "NO"
    println(result)
}
