package day13

import readInput

fun main() {
    data class Point(val x: Int, val y: Int)

    fun List<Point>.visualize() = buildString {
        val width = this@visualize.maxOf { it.x }
        val height = this@visualize.maxOf { it.y }
        for (y in 0..height) {
            for (x in 0..width) {
                if (this@visualize.any { it.x == x && it.y == y }) append('#') else append(' ')
            }
            appendLine()
        }
    }

    fun parseInput(input: List<String>): Pair<List<Point>, List<Pair<String, Int>>> {
        val points = input.takeWhile { it.isNotBlank() }
            .map {
                val (x, y) = it.split(",")
                Point(x.toInt(), y.toInt())
            }

        val instructions = input.takeLastWhile { it.isNotBlank() }
            .map {
                val (direction, position) = it.removePrefix("fold along ").split("=")
                Pair(direction, position.toInt())
            }
        return Pair(points, instructions)
    }

    fun foldHorizontal(points: List<Point>, position: Int): List<Point> {
        return points.map { point ->
            when {
                point.y < position -> point
                else -> Point(point.x, point.y - 2 * (point.y - position))
            }
        }
    }

    fun foldVertical(points: List<Point>, position: Int): List<Point> {
        return points.map { point ->
            when {
                point.x < position -> point
                else -> Point(point.x - 2 * (point.x - position), point.y)
            }
        }
    }

    fun part1(input: List<String>): Int {
        val (cor, ins) = parseInput(input)
        var foldedPaper: List<Point> = emptyList()
        if (ins[0].first == "y") {
            foldedPaper = foldHorizontal(cor, ins[0].second)
        }
        if (ins[0].first == "x") {
            foldedPaper = foldVertical(cor, ins[0].second)
        }
        return foldedPaper.distinct().size
    }

    fun part2(input: List<String>): Int {
        var (cor, ins) = parseInput(input)
        var foldedPaper: List<Point> = cor
        ins.forEach { instruction ->
            foldedPaper = when (instruction.first) {
                "y" -> foldHorizontal(cor, instruction.second)
                else -> foldVertical(cor, instruction.second)
            }
            cor = foldedPaper
        }
        println(foldedPaper.visualize())
        return foldedPaper.distinct().size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day13/Day13_test")
    check(part1(testInput) == 17)
    part2(testInput)

    val input = readInput("day13/Day13")
    println(part1(input))
    println(part2(input))
}