package day06

import readInput


fun main() {


    fun part1(input: List<String>,day:Int): Int {
        var fishList = input[0].split(",").map { it.toInt() }
        for (i in 0 until day){
            var newFishNum = 0
            fishList =fishList.map { timer ->
                if (timer>0){
                    timer-1
                }else{
                    newFishNum++
                    6
                }
            }
            val newFish : List<Int> = List<Int>(newFishNum){8}
            fishList = fishList+newFish
        }
//        println("part1 ${ fishList.joinToString() }")
        return fishList.size
    }

    fun part2(input: List<String>,day:Int): Long {
        var clockFreq = HashMap<Int,Long>()
        input[0].split(",").map { it.toInt() }.forEach {
            clockFreq[it] = (clockFreq[it]?:0L)+1
        }

        for (i in 0 until day){
            val newClock = HashMap<Int,Long>()
            clockFreq.forEach { (freq, num) ->
                if (freq ==0){
                    newClock[6] = (newClock[6]?:0) + num
                    newClock[8] = num
                }else{
                    newClock[freq-1] = (newClock[freq-1]?:0)+num
                }
            }
            clockFreq = newClock
        }
//        println("part2:$clockFreq}")
        return clockFreq.map { it.value }.sum().toLong()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day06/Day06_test")
    check(part1(testInput,18) == 26)
    check(part1(testInput,80) == 5934)
    check(part2(testInput,256) == 26984457539)

    val input = readInput("day06/Day06")
    println(part1(input,80))
    println(part2(input,256))
}


