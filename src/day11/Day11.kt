package day11

import readInput

fun main() {
    data class Octopus(var energy: Int, var flashed: Boolean = false)

    fun flashNum(inputNums: List<List<Octopus>>): Int {
        val rowNum = inputNums.size
        val colNum = inputNums[0].size
        var result = 0

        for (i in 0 until rowNum) {
            for (j in 0 until colNum) {
                inputNums[i][j].energy++
                inputNums[i][j].flashed = false
            }
        }

        var toFlash = inputNums.flatten().count { it.energy >= 10 }
        while (toFlash > 0) {
            for (i in 0 until rowNum) {
                for (j in 0 until colNum) {
                    if (inputNums[i][j].energy > 9) {
                        inputNums[i][j].energy = 0
                        inputNums[i][j].flashed = true
                        result++

                        val neighbour = listOfNotNull(
                            inputNums[i].getOrNull(j - 1),
                            inputNums[i].getOrNull(j + 1),
                            inputNums.getOrNull(i - 1)?.get(j),
                            inputNums.getOrNull(i - 1)?.getOrNull(j - 1),
                            inputNums.getOrNull(i - 1)?.getOrNull(j + 1),
                            inputNums.getOrNull(i + 1)?.get(j),
                            inputNums.getOrNull(i + 1)?.getOrNull(j - 1),
                            inputNums.getOrNull(i + 1)?.getOrNull(j + 1),
                        )
                        neighbour.filter { !it.flashed }.forEach { it.energy++ }
                    }
                }
            }
            toFlash = inputNums.flatten().count { it.energy >= 10 }
        }

        return result
    }

    fun part1(input: List<String>, steps: Int): Int {
        val inputNums = input.map { line ->
            line.toList().map { char ->
                Octopus(char - '0')
            }
        }

        var result = 0
        for (step in 0 until steps) {
            result += flashNum(inputNums)
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val inputNums = input.map { line ->
            line.toList().map { char ->
                Octopus(char - '0')
            }
        }

        var allFlashed = false
        var step = 0
        while (!allFlashed) {
            step++
            flashNum(inputNums)
            if (inputNums.flatten().filter { it.flashed }.size == 100) {
                allFlashed = true
            }
        }
        return step
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day11/Day11_test")
    check(part1(testInput, 100) == 1656)
    check(part2(testInput) == 195)

    val input = readInput("day11/Day11")
    println(part1(input, 100))
    println(part2(input))
}