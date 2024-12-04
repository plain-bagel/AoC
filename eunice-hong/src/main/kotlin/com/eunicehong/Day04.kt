package com.eunicehong

/**
 * # 2024 Day 4: Ceres Search
 *
 * Copyright (c) Eric Wastl
 *
 * #### [Direct Link](https://adventofcode.com/2024/day/4)
 *
 */
internal class Day04 {
    /**
     * ## Part 1
     *
     * "Looks like the Chief's not here. Next!" One of The Historians pulls out a device and pushes the only button on it. After a brief flash, you recognize the interior of the Ceres monitoring station!
     *
     * As the search for the Chief continues, a small Elf who lives on the station tugs on your shirt; she'd like to know if you could help her with her word search (your puzzle input). She only has to find one word: `XMAS`.
     *
     * This word search allows words to be horizontal, vertical, diagonal, written backwards, or even overlapping other words. It's a little unusual, though, as you don't merely need to find one instance of `XMAS` - you need to find all of them. Here are a few ways `XMAS` might appear, where irrelevant characters have been replaced with `.`:
     *
     * ```
     * ..X...
     * .SAMX.
     * .A..A.
     * XMAS.S
     * .X....
     * ```
     * The actual word search will be full of letters instead. For example:
     *
     * ```
     * MMMSXXMASM
     * MSAMXMSMSA
     * AMXSXMAAMM
     * MSAMASMSMX
     * XMASAMXAMM
     * XXAMMXXAMA
     * SMSMSASXSS
     * SAXAMASAAA
     * MAMMMXMMMM
     * MXMXAXMASX
     * ```
     * In this word search, `XMAS` occurs a total of 18 times; here's the same word search again, but where letters not involved in any `XMAS` have been replaced with .:
     *
     * ```
     * ....XXMAS.
     * .SAMXMS...
     * ...S..A...
     * ..A.A.MS.X
     * XMASAMX.MM
     * X.....XA.A
     * S.S.S.S.SS
     * .A.A.A.A.A
     * ..M.M.M.MM
     * .X.X.XMASX
     * ```
     *
     * Take a look at the little Elf's word search. How many times does `XMAS` appear?
     */
    fun solution1(puzzle: String): String {
        val lines = puzzle.lines()
        return (0 until lines.size * lines[0].length)
            .sumOf { index ->
                val x = index % lines.size
                val y = index / lines.size

                // Check if it can visit X from the current position
                if (lines[y][x] != 'X') return@sumOf 0

                // Check if it can visit M
                lines
                    .canVisit(x, y, 'M')
                    .filter { (direction, mx, my) ->

                        // Check if it can visit A from M in the same direction
                        lines
                            .canVisit(direction, mx, my, 'A')
                            ?.takeIf { triple -> triple.first == direction }
                            ?.let { (_, ax, ay) ->

                                // Check if it can visit S from A in the same direction
                                lines
                                    .canVisit(direction, ax, ay, 'S')
                                    ?.takeIf { triple -> triple.first == direction } ?: return@filter false
                                true
                            } ?: false
                    }.count()
            }.toString()
    }

    /**
     * ## Part 2
     *
     */
    fun solution2(puzzle: String): String {
        val lines = puzzle.lines().filter { it.isNotEmpty() }
        return ""
    }

    private fun List<String>.canVisit(
        x: Int,
        y: Int,
        char: Char,
    ): List<Triple<Direction, Int, Int>> =
        Direction.entries.mapNotNull { direction ->
            this.canVisit(direction, x, y, char)
        }

    private fun List<String>.canVisit(
        direction: Direction,
        x: Int,
        y: Int,
        char: Char,
    ): Triple<Direction, Int, Int>? {
        val i = direction.value
        val yToVisit = (y - 1 + i / 3).takeIf { it in indices } ?: return null
        val xToVisit = (x - 1 + i % 3).takeIf { it in this[yToVisit].indices } ?: return null
        return if (this[yToVisit][xToVisit].equals(char, ignoreCase = true)) {
            Triple(Direction.fromValue(i), xToVisit, yToVisit)
        } else {
            null
        }
    }
}

enum class Direction(
    val value: Int,
) {
    UP_LEFT(0),
    UP(1),
    UP_RIGHT(2),
    LEFT(3),
    RIGHT(5),
    DOWN_LEFT(6),
    DOWN(7),
    DOWN_RIGHT(8),
    ;

    companion object {
        fun fromValue(value: Int): Direction = entries.first { it.value == value }
    }
}
