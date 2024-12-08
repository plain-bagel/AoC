package com.eunicehong

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Check if the solutions for [Day08] is correct.
 */
internal class Day08Test : DailyTest(8) {
    private val testDay08: Day08 = Day08()

    @Test
    fun puzzleSolution1Example() {
        val exampleInput =
            """
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............
            """.trimIndent()
        val expected = "14"
        val actual = testDay08.solution1(exampleInput)
        assertEquals(expected, actual, "🎅 Solution for Example 1 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Example 1 completed! $actual")
    }

    @Test
    override fun puzzleSolution1() {
        val expected = "Not implemented"
        val actual = testDay08.solution1(input)
        assertEquals(expected, actual, "🎅 Solution for Puzzle 1 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Puzzle 1 completed! $actual")
    }

    @Test
    override fun puzzleSolution2() {
        val expected = "Not implemented"
        val actual = testDay08.solution2(input)
        assertEquals(expected, actual, "🎅 Solution for Puzzle 2 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Puzzle 2 completed! $actual")
    }
}
