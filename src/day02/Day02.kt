package day02

import readInput

/**
 * --- Day 2: Dive! ---
Now, you need to figure out how to pilot this thing.

It seems like the submarine can take a series of commands like forward 1, down 2, or up 3:

forward X increases the horizontal position by X units.
down X increases the depth by X units.
up X decreases the depth by X units.
Note that since you're on a submarine, down and up affect your depth, and so they have the opposite result of what you might expect.

The submarine seems to already have a planned course (your puzzle input). You should probably figure out where it's going. For example:

forward 5
down 5
forward 8
up 3
down 8
forward 2
Your horizontal position and depth both start at 0. The steps above would then modify them as follows:

forward 5 adds 5 to your horizontal position, a total of 5.
down 5 adds 5 to your depth, resulting in a value of 5.
forward 8 adds 8 to your horizontal position, a total of 13.
up 3 decreases your depth by 3, resulting in a value of 2.
down 8 adds 8 to your depth, resulting in a value of 10.
forward 2 adds 2 to your horizontal position, a total of 15.
After following these instructions, you would have a horizontal position of 15 and a depth of 10. (Multiplying these together produces 150.)

Calculate the horizontal position and depth you would have after following the planned course. What do you get if you multiply your final horizontal position by your final depth?*/
fun main() {
    fun sumByDirection(input: List<String>,direction:String):Int{
        return input
                .filter{it.startsWith(direction)}
                .map { it.split(" ")[1].toInt() }.fold(0){init ,next -> init+next}
    }

    fun part1(input: List<String>): Int {
        val horizontal = sumByDirection(input,"forward")
        val depth = sumByDirection(input,"down")
        val up = sumByDirection(input,"up")
        println("horizontal=$horizontal,depth=$depth,up=$up")
        return   horizontal*(depth-up)

    }

    fun part2(input: List<String>): Int {
        var increased = 0
        val inputPairs = input.map { it.split(" ") }
        var horizontal = 0
        var depth = 0
        var aim = 0
        inputPairs.forEach { valuePair ->
            val direction = valuePair[0]
            val value = valuePair[1].toInt()
            when(direction){
                "up" -> aim -= value
                "down" -> aim += value
                else ->{
                    horizontal += value
                    if (aim!=0){
                        depth += aim*value
                    }
                }
            }
        }
        return depth*horizontal
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day02/Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)


    val input = readInput("day02/Day02")
    println(part1(input))
    println(part2(input))
}
