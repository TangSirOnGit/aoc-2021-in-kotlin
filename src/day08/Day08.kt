package day08

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val digits = input.map { it ->
            it.split("|")[1]
                .trim()
                .split(" ")
                .filter { num ->
                    num.length == 2 || num.length == 4 || num.length == 3 || num.length == 7
                }
        }
        return digits.flatten().size
    }

    fun loadDecodeMap(codes: String): HashMap<Char, Char> {
        val decodeMap: HashMap<Char, Char> = HashMap()
        val codeList = codes.split(" ")
        val one = codeList.first { it.length == 2 }
        val seven = codeList.first { it.length == 3 }
        val four = codeList.first { it.length == 4 }
        val a = seven.filter { !one.contains(it) }[0]
        decodeMap[a] = 'a'
        val bd = four.filter { !one.contains(it) }
        val eight = codeList.first { it.length == 7 }
        val eg = eight.filter { !one.contains(it) && !bd.contains(it) && a != it }
        val fiveNums = codeList.filter { it.length == 5 }
        val charNum: HashMap<Char, Int> = HashMap()
        fiveNums.forEach { num ->
            num.forEach {
                charNum[it] = (charNum[it] ?: 0) + 1
            }
        }
        val be = charNum.filter { it.value == 1 && !one.contains(it.key) }.map { it.key }
        val sixNums = codeList.filter { it.length == 6 }
        val sixCharNum: HashMap<Char, Int> = HashMap()
        sixNums.forEach { num ->
            num.forEach {
                sixCharNum[it] = (sixCharNum[it] ?: 0) + 1
            }
        }

        val edc = sixCharNum.filter { it.value == 2 }.map { it.key }
        val e = be.filter { edc.contains(it) }[0]
        val c = edc.filter { one.contains(it) }[0]
        val f = one.filter { it != c }[0]
        val d = edc.filter { bd.contains(it) }[0]
        decodeMap[f] = 'f'
        decodeMap[c] = 'c'
        decodeMap[d] = 'd'
        decodeMap[e] = 'e'
        val g = eg.filter { it != e }[0]
        decodeMap[g] = 'g'
        val b = bd.filter { it != d }[0]
        decodeMap[b] = 'b'

        return decodeMap
    }

    fun decode(dict: HashMap<Char, Char>, orig: String): String {
        val pattenMap = mapOf(
            "abcefg" to "0",
            "cf" to "1",
            "acdeg" to "2",
            "acdfg" to "3",
            "bcdf" to "4",
            "abdfg" to "5",
            "abdefg" to "6",
            "acf" to "7",
            "abcdefg" to "8",
            "abcdfg" to "9"
        )
        val pattern = orig.map { dict[it] }.sortedBy { it }.joinToString(separator = "")
        return pattenMap[pattern] ?: ""
    }

    fun decodeNum(input: String): Int {
        val (codes, nums) = input.split("|")
        val decodeMap = loadDecodeMap(codes)
        val origNum = nums.trim().split(" ")
        val correctNUm = origNum.map { decode(decodeMap, it) }.joinToString(separator = "")
        return correctNUm.toInt()
    }

    fun part2(input: List<String>): Int {
        return input.map { decodeNum(it) }.fold(0) { init, acc -> init + acc }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day08/Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("day08/Day08")
    println(part1(input))
    println(part2(input))
}


