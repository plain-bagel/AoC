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
     * The Elf looks quizzically at you. Did you misunderstand the assignment?
     *
     * Looking for the instructions, you flip over the word search to find that this isn't actually an XMAS puzzle; it's an X-MAS puzzle in which you're supposed to find two MAS in the shape of an X. One way to achieve that is like this:
     *
     * ```
     * M.S
     * .A.
     * M.S
     * ```
     *
     * Irrelevant characters have again been replaced with . in the above diagram. Within the X, each MAS can be written forwards or backwards.
     *
     * Here's the same example from before, but this time all of the X-MASes have been kept instead:
     *
     * ```
     * .M.S......
     * ..A..MSMS.
     * .M.S.MAA..
     * ..A.ASMSM.
     * .M.S.M....
     * ..........
     * S.S.S.S.S.
     * .A.A.A.A..
     * M.M.M.M.M.
     * ..........
     * ```
     * In this example, an `X-MAS` appears 9 times.
     *
     * Flip the word search from the instructions back over to the word search side and try again. How many times does an `X-MAS` appear?
     */
    fun solution2(puzzle: String): String {
        val lines = puzzle.lines()
        return (0 until lines.size * lines[0].length)
            .map { index ->
                val x = index % lines.size
                val y = index / lines.size
                x to y
            }.filter { (x, y) ->
                // Check if the current position is M
                lines[y][x] == 'M'
            }.flatMap { (x, y) ->
                // All the possible directions to draw the X
                Direction.drawBigX
                    .mapNotNull { direction ->
                        BigX.create(direction, x, y).takeIf { bigX -> lines.isValid(bigX) }
                    }
            }.toSet()
            .size
            .toString()
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

    /**
     * Check if the X is valid.
     */
    private fun List<String>.isValid(bigX: BigX): Boolean {
        // 1. Check if the X is within the bounds of the list
        bigX.coordinates
            .all { (x, y) -> y in indices && x in this[y].indices }
            .takeIf { it } ?: return false
        val mList = bigX.coordinates.filter { (x, y) -> this[y][x] == 'M' }
        val sList = bigX.coordinates.filter { (x, y) -> this[y][x] == 'S' }

        // 2. Check if the X has 2 M's and 2 S's in the same line
        val mInSameLine = mList.all { (x, y) -> x == mList.firstOrNull()?.first || y == mList.firstOrNull()?.second }
        val sInSameLine = sList.all { (x, y) -> x == sList.firstOrNull()?.first || y == sList.firstOrNull()?.second }
        // 3. Check if the center of the X is A
        val isCenterA = bigX.center.let { (x, y) -> this[y][x] == 'A' }
        return mList.size == 2 && mInSameLine && sList.size == 2 && sInSameLine && isCenterA
    }
}

data class BigX(
    val leftUp: Pair<Int, Int>,
    val center: Pair<Int, Int>,
    val rightUp: Pair<Int, Int>,
    val leftDown: Pair<Int, Int>,
    val rightDown: Pair<Int, Int>,
) {
    /**
     * The coordinates of the X.
     */
    val coordinates = listOf(leftUp, center, rightUp, leftDown, rightDown)

    companion object {
        fun create(
            direction: Direction,
            x: Int,
            y: Int,
        ) = when (direction) {
            Direction.DOWN_LEFT ->
                BigX(
                    leftUp = x - 2 to y,
                    center = x - 1 to y + 1,
                    rightUp = x to y,
                    leftDown = x - 2 to y + 2,
                    rightDown = x to y + 2,
                )

            Direction.DOWN_RIGHT ->
                BigX(
                    leftUp = x to y,
                    center = x + 1 to y + 1,
                    rightUp = x + 2 to y,
                    leftDown = x to y + 2,
                    rightDown = x + 2 to y + 2,
                )

            Direction.UP_LEFT ->
                BigX(
                    leftUp = x - 2 to y - 2,
                    center = x - 1 to y - 1,
                    rightUp = x to y - 2,
                    leftDown = x - 2 to y,
                    rightDown = x to y,
                )

            else ->
                BigX(
                    leftUp = x to y - 2,
                    center = x + 1 to y - 1,
                    rightUp = x + 2 to y - 2,
                    leftDown = x to y,
                    rightDown = x + 2 to y,
                )
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
        val drawBigX = listOf(DOWN_LEFT, DOWN_RIGHT, UP_LEFT, UP_RIGHT)

        fun fromValue(value: Int): Direction = entries.first { it.value == value }
    }
}
