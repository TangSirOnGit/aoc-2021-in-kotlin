package day07

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var minSum = Int.MAX_VALUE
        val positions = input[0].split(",").map { it.toInt() }
        positions.forEach { dest ->
            var curSum = 0
            positions.forEach { pos ->
                curSum+= Math.abs(pos-dest)
            }
            minSum = Math.min(minSum,curSum)
        }

        println("part1,$minSum")
        return minSum
    }

    fun part2(input: List<String>): Int {
        var minSum = Int.MAX_VALUE
        val positions = input[0].split(",").map { it.toInt() }
        val longest = positions.maxOf { it }
        (0..longest).forEach { dest ->
            var curSum = 0
            positions.forEach { pos ->
                val dis = Math.abs(pos-dest)
                curSum+= ((1+dis)*dis)/2
            }
            minSum = Math.min(minSum,curSum)
        }

//        println("part2,$minSum")
        return minSum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day07/Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("day07/Day07")
    println(part1(input))
    println(part2(input))
}


