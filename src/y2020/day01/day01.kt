package y2020.day02
import readInput
fun main() {
    fun part1(input: List<String>): Int {
        val cache = HashSet<Int>()
        input.map { it.toInt() }.forEach {
            val min = 2020 - it
            if (cache.contains(min)){
                return it*min
            }else{
                cache.add(it)
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        val cache = HashSet<Int>()

        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("y2020/day01/Day01_test")
    check(part1(testInput) == 514579)
    check(part1(testInput) == 241861950)

    val input = readInput("y2020/day01/Day01")
    println(part1(input))
//    println(part2(input))
}
