package day12

import readInput

fun main() {
    data class Cave(val name:String){
        val neighbour:ArrayList<Cave> = ArrayList()
        fun canVisitMoreTimes():Boolean{
            return name.uppercase() == name
        }

        fun addNeighbour(cave:Cave){
            neighbour.add(cave)
        }

        fun paths(route:Set<Cave>):Int{
            if (this.name == "end") return 1
            val newRoute = route.plus(this)
            val newNeigh = neighbour.filter {
                it.canVisitMoreTimes() || !newRoute.contains(it)}
            if (newNeigh.isEmpty()) return 0
            return newNeigh.map { it.paths(newRoute) }.fold(0){init, acc -> init+acc}
        }
    }

    fun parseCaveMap(input: List<String>): HashMap<String, Cave> {
        val caveMap: HashMap<String, Cave> = HashMap()
        input.forEach { line ->
            val (lN, rN) = line.split("-")
            val lC = caveMap.getOrDefault(lN, Cave(lN))
            val rC = caveMap.getOrDefault(rN, Cave(rN))
            lC.addNeighbour(rC)
            rC.addNeighbour(lC)
            caveMap.putIfAbsent(lN, lC)
            caveMap.putIfAbsent(rN, rC)
        }
        return caveMap
    }

    fun part1(input: List<String>): Int {
        val caveMap: HashMap<String, Cave> = parseCaveMap(input)
        val route:Set<Cave> = emptySet()
        val start = caveMap["start"]
        return start!!.paths(route)
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day12/Day12_test")
    check(part1(testInput) == 10)
//    check(part2(testInput) == 36)

    val input = readInput("day12/Day12")
    println(part1(input))
//    println(part2(input))
}