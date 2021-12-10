package day10

import readInput
import java.util.*

fun main() {


    fun part1(input: List<String>): Int {
        val leftSet = setOf('(','[','<','{')
        var score = 0
        val pairMap = mapOf(
            ')' to '(',
            ']' to '[',
            '>' to '<',
            '}' to '{'
        )
        val scoreMap = mapOf(
            ')' to 3,
            ']' to 57,
            '>' to 25137,
            '}' to 1197
        )
        val viewed = Stack<Char>()
        input.joinToString(separator = "").forEach { char->
            if (leftSet.contains(char)){
                viewed.add(char)
            }else{
                val left = viewed.pop()
                if (left != pairMap[char]) score += scoreMap[char]?:0
            }
        }
        return score

    }
    fun corrupted(input:String):Boolean{
        val leftSet = setOf('(','[','<','{')
        val pairMap = mapOf(
            ')' to '(',
            ']' to '[',
            '>' to '<',
            '}' to '{'
        )
        val viewed = Stack<Char>()
        input.forEach { char->
            if (leftSet.contains(char)){
                viewed.add(char)
            }else{
                if (viewed.pop() != pairMap[char]) return true
            }
        }

        return false
    }
    fun remain(input:String): List<Char> {
        val leftSet = setOf('(','[','<','{')
        val viewed = Stack<Char>()
        input.forEach { char->
            if (leftSet.contains(char)) viewed.add(char) else viewed.pop()
        }
        return viewed.toList()
    }
    fun score(input:List<Char>): Long {
        val scoreMap = mapOf(
            '(' to 1,
            '[' to 2,
            '<' to 4,
            '{' to 3
        )
        var score = 0L
        for(i in input.size-1 downTo 0){
            score = score*5 + (scoreMap[input[i]]?:0)
        }
        return score
    }
    fun part2(input: List<String>): Long {
        val scores = input.filter { !corrupted(it) }.map { score(remain(it)) }.sorted()
        return scores[scores.size/2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day10/Day10_test")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)


    val input = readInput("day10/Day10")
//    println(part1(input))
    println(part2(input))
}