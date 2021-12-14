package day14

import readInput

fun main() {
    fun insertion(start: Map<String, Long>, rule: Map<String, Pair<String, String>>): Map<String, Long> {
        val result = HashMap<String, Long>()
        start.forEach { (ori, num) ->
            val (left, right) = rule[ori] ?: Pair("", "")
            result[left] = (result[left] ?: 0) + num
            result[right] = (result[right] ?: 0) + num
        }
        return result
    }

    fun part2(input: List<String>, steps: Int): Long {
        val start = input.takeWhile { it.isNotBlank() }[0]
        val startMap = HashMap<String, Long>()
        start.zipWithNext().forEach {
            val key = "${it.first}${it.second}"
            startMap[key] = (startMap[key] ?: 0) + 1
        }
        val rules = input.takeLastWhile { it.isNotBlank() }
            .associate { line ->
                val left = line[0]
                val right = line[1]
                val mid = line[6]
                "${left}${right}" to Pair("${left}${mid}", "${mid}${right}")
            }

        var result: Map<String, Long> = startMap
        for (i in 0 until steps) {
            result = insertion(result, rules)
        }

        val charMap: HashMap<Char, Long> = HashMap()
        result.forEach { (str, num) ->
            charMap[str[0]] = (charMap[str[0]] ?: 0) + num
            charMap[str[1]] = (charMap[str[1]] ?: 0) + num
        }
        return (charMap.maxOf { it.value } + 1) / 2 - (charMap.minOf { it.value } + 1) / 2
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day14/Day14_test")
    check(part2(testInput, 10) == 1588L)
    check(part2(testInput, 40) == 2188189693529L)

    val input = readInput("day14/Day14")
    println(part2(input, 10))
    println(part2(input, 40))
}