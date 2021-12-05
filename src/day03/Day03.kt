package day03

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        val elementNum = input[0].length
        val nums = Array(elementNum){0}
        input.forEach { item ->
            for (index in item.indices){
                val char = item[index]
                if (char == '1') nums[index] += 1 else nums[index] -= 1
            }
        }
        val gammaStr = nums.map { sum-> if (sum>0) 1 else 0 }.joinToString(separator = "")
        val epsilonStr = nums.map { sum-> if (sum<=0) 1 else 0 }.joinToString(separator = "")
//        println("numStr=$gammaStr,epsilonStr=$epsilonStr")
        val gamma = Integer.parseInt( gammaStr,2)
        val epsilon = Integer.parseInt( epsilonStr,2)
//        println("gamma=$gamma,epsilon=$epsilon")
        return gamma*epsilon

    }

    fun positive(content:List<Char>):Boolean{
        val sum = content.map { char-> if (char == '1') 1 else -1 }.fold(0){init ,next -> init+next}
        return sum>=0
    }

    fun part2(input: List<String>): Int {
        val elementNum = input[0].length
        var oxygenContent = input
        var co2Content = input
        for (index in 0 until elementNum){
            if (oxygenContent.size <2){
                break
            }
            val cur = oxygenContent.map { it[index] }
            oxygenContent = if (positive(cur)){
                oxygenContent.filter { it[index] =='1' }
            }else{
                oxygenContent.filter { it[index] =='0' }
            }
        }

        for (index in 0 until elementNum){
            if (co2Content.size <2){
                break
            }
            val cur = co2Content.map { it[index] }
            co2Content = if (!positive(cur)){
                co2Content.filter { it[index] =='1' }
            }else{
                co2Content.filter { it[index] =='0' }
            }
        }

//        println("$oxygenContent,$co2Content")
        val oxygen = Integer.parseInt( oxygenContent.joinToString(separator = ""),2)
        val c02 = Integer.parseInt( co2Content.joinToString(separator = ""),2)
        return oxygen*c02
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day03/Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)


    val input = readInput("day03/Day03")
    println(part1(input))
    println(part2(input))
}
