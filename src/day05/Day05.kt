package day05

import readInput


/***
 * --- Day 5: Hydrothermal Venture ---
You come across a field of hydrothermal vents on the ocean floor! These vents constantly produce large, opaque clouds, so it would be best to avoid them if possible.

They tend to form in lines; the submarine helpfully produces a list of nearby lines of vents (your puzzle input) for you to review. For example:

0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2
Each line of vents is given as a line segment in the format x1,y1 -> x2,y2 where x1,y1 are the coordinates of one end the line segment and x2,y2 are the coordinates of the other end. These line segments include the points at both ends. In other words:

An entry like 1,1 -> 1,3 covers points 1,1, 1,2, and 1,3.
An entry like 9,7 -> 7,7 covers points 9,7, 8,7, and 7,7.
For now, only consider horizontal and vertical lines: lines where either x1 = x2 or y1 = y2.

So, the horizontal and vertical lines from the above list would produce the following diagram:

.......1..
..1....1..
..1....1..
.......1..
.112111211
..........
..........
..........
..........
222111....
In this diagram, the top left corner is 0,0 and the bottom right corner is 9,9. Each position is shown as the number of lines which cover that point or . if no line covers that point. The top-left pair of 1s, for example, comes from 2,2 -> 2,1; the very bottom row is formed by the overlapping lines 0,9 -> 5,9 and 0,9 -> 2,9.

To avoid the most dangerous areas, you need to determine the number of points where at least two lines overlap. In the above example, this is anywhere in the diagram with a 2 or larger - a total of 5 points.

Consider only horizontal and vertical lines. At how many points do at least two lines overlap?*/
fun main() {
    fun loadCoveredPointNum(
        input: List<String>,
        worker: (Line) -> Boolean
    ): Int {
        val lines = input.map { Line.lineFrom(it) }.filter { worker(it) }
        val coveredNum: HashMap<Point, Int> = HashMap()
        lines.forEach { line ->
            val points = line.coveredPoints()
            points.forEach { point ->
                coveredNum[point] = (coveredNum[point] ?: 0) + 1
            }
        }

        return coveredNum.filter { it.value >= 2 }.size
    }

    fun part1(input: List<String>): Int {
        val worker:(Line)->Boolean = {it.isVertical() || it.isHorizontal()}
        return  loadCoveredPointNum(input, worker)
    }

    fun part2(input: List<String>): Int {
        val worker:(Line)->Boolean = {it.isVertical() || it.isHorizontal() || it.isDiagonal()}
        return loadCoveredPointNum(input, worker)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day05/Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("day05/Day05")
    println(part1(input))
    println(part2(input))
}

data class Point(val x:Int,val y:Int){
    companion object{
        fun pointOf(cord:String):Point{
            val (x,y) = cord.split(",").map { it.trim().toInt() }
            return Point(x,y)
        }
    }
}
data class Line(val start:Point, val end:Point){
    fun isHorizontal():Boolean{
        return start.y == end.y
    }

    fun isVertical():Boolean{
        return  start.x == end.x
    }

    fun isDiagonal():Boolean{
        return Math.abs(start.x-end.x) == Math.abs(start.y-end.y)
    }

    fun coveredPoints():List<Point>{
        if (isHorizontal()){
            val from = Math.min(start.x,end.x)
            val end = Math.max(start.x,end.x)
           return (from .. end).map { Point(it,start.y) }
        }

        if (isVertical()){
            val from = Math.min(start.y,end.y)
            val end = Math.max(start.y,end.y)
            return (from .. end).map { Point(start.x,it) }
        }

        if (isDiagonal()){
            val length = Math.abs(end.x -start.x)
            val (from,to)  = (if (start.x<end.x) Pair(start,end) else Pair(end,start))
            val yStep = if(from.y< to.y) 1 else -1

            val points =  (from.x .. from.x+length).map {
                Point(it,from.y+((it-from.x)*yStep))
            }
            return points
        }

        return emptyList()
    }
    companion object{
        fun lineFrom(lineStr:String):Line{
            val (start,end) = lineStr.split("->")
                .map { Point.pointOf(it.trim()) }
            return Line(start,end)
        }
    }
}
