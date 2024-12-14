package com.eunicehong

/**
 * # 2024 Day 14: Restroom Redoubt
 *
 * Copyright (c) Eric Wastl
 *
 * #### [Direct Link](https://adventofcode.com/2024/day/14)
 *
 */
internal class Day14 {
    /**
     * ## Part 1
     *
     * One of The Historians needs to use the bathroom; fortunately, you know there's a bathroom near an unvisited location on their list, and so you're all quickly teleported directly to the lobby of Easter Bunny Headquarters.
     *
     * Unfortunately, EBHQ seems to have "improved" bathroom security again after your last visit. The area outside the bathroom is swarming with robots!
     *
     * To get The Historian safely to the bathroom, you'll need a way to predict where the robots will be in the future. Fortunately, they all seem to be moving on the tile floor in predictable straight lines.
     *
     * You make a list (your puzzle input) of all of the robots' current positions (p) and velocities (v), one robot per line. For example:
     *
     * ```
     * p=0,4 v=3,-3
     * p=6,3 v=-1,-3
     * p=10,3 v=-1,2
     * p=2,0 v=2,-1
     * p=0,0 v=1,3
     * p=3,0 v=-2,-2
     * p=7,6 v=-1,-3
     * p=3,0 v=-1,-2
     * p=9,3 v=2,3
     * p=7,3 v=-1,2
     * p=2,4 v=2,-3
     * p=9,5 v=-3,-3
     * ```
     * Each robot's position is given as p=x,y where x represents the number of tiles the robot is from the left wall and y represents the number of tiles from the top wall (when viewed from above). So, a position of p=0,0 means the robot is all the way in the top-left corner.
     *
     * Each robot's velocity is given as v=x,y where x and y are given in tiles per second. Positive x means the robot is moving to the right, and positive y means the robot is moving down. So, a velocity of v=1,-2 means that each second, the robot moves 1 tile to the right and 2 tiles up.
     *
     * The robots outside the actual bathroom are in a space which is 101 tiles wide and 103 tiles tall (when viewed from above). However, in this example, the robots are in a space which is only 11 tiles wide and 7 tiles tall.
     *
     * The robots are good at navigating over/under each other (due to a combination of springs, extendable legs, and quadcopters), so they can share the same tile and don't interact with each other. Visually, the number of robots on each tile in this example looks like this:
     *
     * ```
     * 1.12.......
     * ...........
     * ...........
     * ......11.11
     * 1.1........
     * .........1.
     * .......1...
     * ```
     *
     * These robots have a unique feature for maximum bathroom security: they can teleport. When a robot would run into an edge of the space they're in, they instead teleport to the other side, effectively wrapping around the edges. Here is what robot p=2,4 v=2,-3 does for the first few seconds:
     *
     * Initial state:
     * ```
     * ...........
     * ...........
     * ...........
     * ...........
     * ..1........
     * ...........
     * ...........
     *```
     * After 1 second:
     * ```
     * ...........
     * ....1......
     * ...........
     * ...........
     * ...........
     * ...........
     * ...........
     *```
     * After 2 seconds:
     * ```
     * ...........
     * ...........
     * ...........
     * ...........
     * ...........
     * ......1....
     * ...........
     *```
     * After 3 seconds:
     * ```
     * ...........
     * ...........
     * ........1..
     * ...........
     * ...........
     * ...........
     * ...........
     *```
     * After 4 seconds:
     * ```
     * ...........
     * ...........
     * ...........
     * ...........
     * ...........
     * ...........
     * ..........1
     *```
     * After 5 seconds:
     * ```
     * ...........
     * ...........
     * ...........
     * .1.........
     * ...........
     * ...........
     * ...........
     * ```
     * The Historian can't wait much longer, so you don't have to simulate the robots for very long. Where will the robots be after 100 seconds?
     *
     * In the above example, the number of robots on each tile after 100 seconds has elapsed looks like this:
     *
     * ```
     * ......2..1.
     * ...........
     * 1..........
     * .11........
     * .....1.....
     * ...12......
     * .1....1....
     * ```
     * To determine the safest area, count the number of robots in each quadrant after 100 seconds. Robots that are exactly in the middle (horizontally or vertically) don't count as being in any quadrant, so the only relevant robots are:
     *
     * ```
     * ..... 2..1.
     * ..... .....
     * 1.... .....
     *
     * ..... .....
     * ...12 .....
     * .1... 1....
     * ```
     * In this example, the quadrants contain 1, 3, 4, and 1 robot. Multiplying these together gives a total safety factor of 12.
     *
     * Predict the motion of the robots in your list within a space which is 101 tiles wide and 103 tiles tall. What will the safety factor be after exactly 100 seconds have elapsed?
     */
    fun solution1(puzzle: String): String {
        val robots =
            puzzle
                .lines()
                .filter { it.isNotEmpty() }
                .getRobots()
        val width = robots.maxOf { it.x } + 1
        val height = robots.maxOf { it.y } + 1
        return robots
            .asSequence()
            .map { it.move(width, height, 100) }
            .groupBy { it.x to it.y }
            .asSequence()
            .map { it.key to it.value.size }
            .filter { (position, _) -> position.first != (width / 2) && position.second != (height / 2) }
            .groupBy { (position, _) -> (position.first < (width / 2)) to (position.second < (height / 2)) }
            .map { (quadrant, robots) -> quadrant to robots.sumOf { it.second } }
            .fold(1) { acc, (_, count) -> acc * count }
            .toString()
    }

    /**
     * ## Part 2
     *
     * During the bathroom break, someone notices that these robots seem awfully similar to ones built and used at the North Pole. If they're the same type of robots, they should have a hard-coded Easter egg: very rarely, most of the robots should arrange themselves into a picture of a Christmas tree.
     *
     * What is the fewest number of seconds that must elapse for the robots to display the Easter egg?
     *
     *
     */
    fun solution2(puzzle: String): String {
        val lines = puzzle.lines().filter { it.isNotEmpty() }
        return ""
    }

    private val digitRegex = Regex("""-?\d+""")

    private fun List<String>.getRobots(): List<Robot> =
        map { line ->
            val (x, y, vx, vy) = digitRegex.findAll(line).map { it.value.toInt() }.toList()
            Robot(x, y, vx, vy)
        }

    private data class Robot(
        val x: Int,
        val y: Int,
        val vx: Int,
        val vy: Int,
    ) {
        fun move(
            width: Int,
            height: Int,
            times: Int,
        ): Robot =
            (0 until times).fold(this) { robot, _ ->
                val x = (robot.x + robot.vx + width) % width
                val y = (robot.y + robot.vy + height) % height
                Robot(x, y, robot.vx, robot.vy)
            }
    }
}
