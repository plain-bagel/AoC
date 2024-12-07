package com.eunicehong

/**
 * # 2024 Day 6: Guard Gallivant
 *
 * Copyright (c) Eric Wastl
 *
 * #### [Direct Link](https://adventofcode.com/2024/day/6)
 *
 */
internal class Day06 {
    /**
     * ## Part 1
     *
     * The Historians use their fancy device again, this time to whisk you all away to the North Pole prototype suit manufacturing lab... in the year 1518! It turns out that having direct access to history is very convenient for a group of historians.
     *
     * You still have to be careful of time paradoxes, and so it will be important to avoid anyone from 1518 while The Historians search for the Chief. Unfortunately, a single guard is patrolling this part of the lab.
     *
     * Maybe you can work out where the guard will go ahead of time so that The Historians can search safely?
     *
     * You start by making a map (your puzzle input) of the situation. For example:
     *
     * ```
     * ....#.....
     * .........#
     * ..........
     * ..#.......
     * .......#..
     * ..........
     * .#..^.....
     * ........#.
     * #.........
     * ......#...
     * ```
     * The map shows the current position of the guard with ^ (to indicate the guard is currently facing up from the perspective of the map). Any obstructions - crates, desks, alchemical reactors, etc. - are shown as #.
     *
     * Lab guards in 1518 follow a very strict patrol protocol which involves repeatedly following these steps:
     *
     * If there is something directly in front of you, turn right 90 degrees.
     * Otherwise, take a step forward.
     * Following the above protocol, the guard moves up several times until she reaches an obstacle (in this case, a pile of failed suit prototypes):
     *
     * ```
     * ....#.....
     * ....^....#
     * ..........
     * ..#.......
     * .......#..
     * ..........
     * .#........
     * ........#.
     * #.........
     * ......#...
     * ```
     * Because there is now an obstacle in front of the guard, she turns right before continuing straight in her new facing direction:
     *
     * ```
     * ....#.....
     * ........>#
     * ..........
     * ..#.......
     * .......#..
     * ..........
     * .#........
     * ........#.
     * #.........
     * ......#...
     * ```
     *
     * Reaching another obstacle (a spool of several very long polymers), she turns right again and continues downward:
     *
     * ```
     * ....#.....
     * .........#
     * ..........
     * ..#.......
     * .......#..
     * ..........
     * .#......v.
     * ........#.
     * #.........
     * ......#...
     * ```
     *
     * This process continues for a while, but the guard eventually leaves the mapped area (after walking past a tank of universal solvent):
     *
     * ```
     * ....#.....
     * .........#
     * ..........
     * ..#.......
     * .......#..
     * ..........
     * .#........
     * ........#.
     * #.........
     * ......#v..
     * ```
     *
     * By predicting the guard's route, you can determine which specific positions in the lab will be in the patrol path. Including the guard's starting position, the positions visited by the guard before leaving the area are marked with an X:
     *
     * ```
     * ....#.....
     * ....XXXXX#
     * ....X...X.
     * ..#.X...X.
     * ..XXXXX#X.
     * ..X.X.X.X.
     * .#XXXXXXX.
     * .XXXXXXX#.
     * #XXXXXXX..
     * ......#X..
     * ```
     * In this example, the guard will visit 41 distinct positions on your map.
     *
     * Predict the path of the guard. How many distinct positions will the guard visit before leaving the mapped area?
     *
     *
     */
    fun solution1(puzzle: String): String {
        var lines = puzzle.lines().filter { it.isNotEmpty() }
        val puzzleWidth = lines.first().length
        val puzzleHeight = lines.size
        val guardPosition = lines.getPositionOf(CHAR_GUARD).first()
        val obstructions = lines.getPositionOf(CHAR_OBSTRUCTION).map(::Obstruction)
        var guard = Guard(guardPosition, Direction.UP)
        do {
            val obstruction = guard.getFirstObstruction(puzzleWidth to puzzleHeight, obstructions)
            lines = lines.visit(guard, obstruction)
//            lines.map(::println)
//            println("\n")
            guard = guard.nextPosition(obstruction)
        } while (obstruction.x in 0 until puzzleWidth && obstruction.y in 0 until puzzleHeight)
        return lines.joinToString("").count { it.equals(CHAR_VISITED, ignoreCase = true) }.toString()
    }

    /**
     * ## Part 2
     *
     */
    fun solution2(puzzle: String): String {
        val lines = puzzle.lines().filter { it.isNotEmpty() }
        return ""
    }

    /**
     * Get the first obstruction in the guard's path.
     */
    private fun Guard.getFirstObstruction(
        size: Pair<Int, Int>,
        obstructions: List<Obstruction>,
    ): Obstruction =
        when (direction) {
            Direction.UP ->
                obstructions
                    .filter { obstruction ->
                        x == obstruction.x && obstruction.y < y
                    }.maxByOrNull { it.y } ?: Obstruction(x to -1)
            Direction.RIGHT ->
                obstructions
                    .filter { obstruction ->
                        y == obstruction.y && x < obstruction.x
                    }.minByOrNull { it.x } ?: Obstruction(size.first to y)
            Direction.DOWN ->
                obstructions
                    .filter { obstruction ->
                        x == obstruction.x && y < obstruction.y
                    }.minByOrNull { it.y } ?: Obstruction(x to size.second)
            Direction.LEFT ->
                obstructions
                    .filter { obstruction ->
                        y == obstruction.y && obstruction.x < x
                    }.maxByOrNull { it.x } ?: Obstruction(-1 to y)
        }

    private fun List<String>.getPositionOf(char: Char): List<Pair<Int, Int>> {
        val totalCharCount = size * first().length
        return (0 until totalCharCount)
            .map { number ->
                number % first().length to number / first().length
            }.filter { (x, y) ->
                get(y)[x] == char
            }
    }

    /**
     * Mark 'X' on the map where the guard has visited.
     */
    private fun List<String>.visit(
        guard: Guard,
        obstruction: Obstruction,
    ): List<String> =
        mapIndexed { y, line ->
            line
                .mapIndexed { x, char ->
                    when (guard.direction) {
                        Direction.UP ->
                            if (x == guard.x && y in obstruction.y + 1..guard.y) CHAR_VISITED else char
                        Direction.RIGHT ->
                            if (y == guard.y && x in guard.x until obstruction.x) CHAR_VISITED else char
                        Direction.DOWN ->
                            if (x == guard.x && y in guard.y + 1 until obstruction.y) CHAR_VISITED else char
                        Direction.LEFT ->
                            if (y == guard.y && x in obstruction.x + 1..guard.x) CHAR_VISITED else char
                    }
                }.joinToString("")
        }

    private data class Guard(
        private val position: Pair<Int, Int>,
        val direction: Direction,
    ) : Object(position) {
        fun nextPosition(obstruction: Obstruction): Guard =
            when (direction) {
                Direction.UP -> Guard(position = x to obstruction.y + 1, direction = Direction.RIGHT)
                Direction.RIGHT -> Guard(position = obstruction.x - 1 to y, direction = Direction.DOWN)
                Direction.DOWN -> Guard(position = x to obstruction.y - 1, direction = Direction.LEFT)
                Direction.LEFT -> Guard(position = obstruction.x + 1 to y, direction = Direction.UP)
            }
    }

    private data class Obstruction(
        val position: Pair<Int, Int>,
    ) : Object(position)

    private abstract class Object(
        position: Pair<Int, Int>,
    ) {
        val x = position.first
        val y = position.second
    }

    private enum class Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT,
    }

    companion object {
        private const val CHAR_GUARD = '^'
        private const val CHAR_OBSTRUCTION = '#'
        private const val CHAR_VISITED = 'X'
        private const val CHAR_PATH_VERTICAL = '|'
        private const val CHAR_PATH_HORIZONTAL = '-'
        private const val CHAR_PATH_BOTH = '+'
    }
}
