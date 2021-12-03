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

    fun part2(input: List<String>): Int {

        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
//    check(part2(testInput) == 900)


    val input = readInput("Day03")
    println(part1(input))
//    println(part2(input))
}
