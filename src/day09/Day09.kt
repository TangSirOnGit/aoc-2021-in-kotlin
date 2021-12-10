package day09

import readInput

fun main() {


    fun part1(input: List<String>): Int {
        val rowNum = input.size
        val colNum = input[0].length
        var result = 0
        val nums = input.map { it.toList() }
        for (i in 0 until rowNum){
            for (j in 0 until colNum){
                val b = listOfNotNull(
                    nums[i].getOrNull(j-1),
                    nums[i].getOrNull(j+1),
                    nums.getOrNull(i-1)?.get(j),
                    nums.getOrNull(i+1)?.get(j)
                )
                if (nums[i][j] < b.minOrNull()!!){
                    result += nums[i][j] - '0' +1
                }
            }
        }
        return result
//        val numList= input.joinToString(separator = "")
//        println("part1,$numList")
//        numList.forEachIndexed { index, num ->
//            val row = index/colNum
//            val column = index%colNum
//            when{
//                row ==0 ->{
//                    when(column){
//                        0 ->{
//                            if (num < numList[index+1] && num<numList[index+colNum]){
//                                result+=(num - '0' +1)
//                            }
//                        }
//                        colNum -1 ->{
//                            if (num < numList[index-1] && num<numList[index+colNum]){
//                                result+=(num - '0'  +1)
//                            }
//                        }
//                        else->{
//                            if (num < numList[index-1]&& num < numList[index+1] && num<numList[index+colNum]){
//                                result+=(num - '0'  +1)
//                            }
//                        }
//                    }
//                }
//                row == rowNum-1 ->{
//                    when(column){
//                        0 ->{
//                            if (num < numList[index+1] && num<numList[index-colNum]){
//                                result+=(num - '0'  +1)
//                            }
//                        }
//                        colNum -1 ->{
//                            if (num < numList[index-1] && num<numList[index-colNum]){
//                                result+=(num  - '0' +1)
//                            }
//                        }
//                        else->{
//                            if (num < numList[index-1]&& num < numList[index+1] && num<numList[index-colNum]){
//                                result+=(num  - '0' +1)
//                            }
//                        }
//                    }
//                }
//                column ==0 ->{
//                    if (num < numList[index+1]&& num < numList[index+colNum] && num<numList[index-colNum]){
//                        result+=(num  - '0' +1)
//                    }
//                }
//                column == colNum-1 ->{
//                    if (num < numList[index-1]&& num < numList[index+colNum] && num<numList[index-colNum]){
//                        result+=(num  - '0' +1)
//                    }
//                }
//                else ->{
//                    if (num < numList[index-1]&&num < numList[index+1]&& num < numList[index+colNum] && num<numList[index-colNum]){
//                        result+=(num  - '0' +1)
//                    }
//                }
//            }
//        }
//        return result

    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day09/Day09_test")
    check(part1(testInput) == 15)
//    check(part2(testInput) == 900)


    val input = readInput("day09/Day09")
    println(part1(input))
//    println(part2(input))
}