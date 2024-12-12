package com.eunicehong

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Check if the solutions for [Day12] is correct.
 */
internal class Day12Test : DailyTest(12) {
    private val testDay12: Day12 = Day12()

    @Test
    fun puzzleSolution1Example1() {
        val exampleInput =
            """
            AAAA
            BBCD
            BBCC
            EEEC
            """.trimIndent()
        val expected = "140"
        val actual = testDay12.solution1(exampleInput)
        assertEquals(expected, actual, "🎅 Solution for Example 1 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Puzzle 1 completed! $actual")
    }

    @Test
    fun puzzleSolution1Example2() {
        val exampleInput =
            """
            OOOOO
            OXOXO
            OOOOO
            OXOXO
            OOOOO
            """.trimIndent()
        val expected = "772"
        val actual = testDay12.solution1(exampleInput)
        assertEquals(expected, actual, "🎅 Solution for Example @ Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Puzzle 1 completed! $actual")
    }

    @Test
    fun puzzleSolution1Example3() {
        val exampleInput =
            """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
            """.trimIndent()
        val expected = "1930"
        val actual = testDay12.solution1(exampleInput)
        assertEquals(expected, actual, "🎅 Solution for Example 3 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Puzzle 1 completed! $actual")
    }

    @Test
    override fun puzzleSolution1() {
        val expected = "Not implemented"
        val actual = testDay12.solution1(input)
        assertEquals(expected, actual, "🎅 Solution for Puzzle 1 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Puzzle 1 completed! $actual")
    }

    @Test
    override fun puzzleSolution2() {
        val expected = "Not implemented"
        val actual = testDay12.solution2(input)
        assertEquals(expected, actual, "🎅 Solution for Puzzle 2 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Puzzle 2 completed! $actual")
    }
}
