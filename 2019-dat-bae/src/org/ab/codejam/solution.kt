package org.ab.codejam

import java.lang.RuntimeException
import java.math.BigInteger
import java.util.*
import kotlin.math.min

fun main() {
    val s = Scanner(System.`in`)
    s.use {
        val t = s.nextInt()
        for (case in 1..t) {
            val n = s.nextInt()
            val b = s.nextInt()
            val f = s.nextInt()
            val pattern = createPattern(n, b)
            val response = s.sendPattern(pattern, n, b)
            val locations = analyze(response, n, b)
        }
    }
}

fun analyze(response: BitSet, n: Int, b: Int): List<BrokenWorkerLocation> {
    return listOf()
}

fun Scanner.sendPattern(pattern: BitSet, n: Int, b: Int): BitSet {
    println(pattern.toBinaryString(n))
    var line: String
    do {
        line = nextLine()
    } while (line.trim().isEmpty())
    if (line == "-1") {
        throw RuntimeException()
    }
    val result = BitSet(n - b)
    assert(line.length == n - b)
    for ((i, char) in line.withIndex()) {
        if (char == '1') {
            result.set(i)
        }
    }
    return result
}

data class BrokenWorkerLocation(val range: IntRange, val number: Int)

fun createPattern(n: Int, b: Int): BitSet {
    val pattern = BitSet(n)
    val npacks = n / (2 * b)
    for (i in 0 until npacks) {
        pattern.set(i * 2 * b, (i * 2 + 1) * b)
    }
    val lastIndex = npacks * 2 * b
    pattern.set(lastIndex, min(lastIndex + b, n))
    return pattern
}

fun BitSet.toBinaryString(n: Int) = (0 until n).joinToString(separator = "") { if (this.get(it)) "1" else "0" }