package com.eunicehong

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Check if the solutions for [Day07] is correct.
 */
internal class Day07Test : DailyTest(7) {
    private val testDay07: Day07 = Day07()

    @Test
    fun puzzleSolution1Example() {
        val exampleInput =
            """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
            """.trimIndent()
        val expected = "3749"
        val actual = testDay07.solution1(exampleInput)
        assertEquals(expected, actual, "🎅 Solution for Example 1 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Puzzle 1 completed! $actual")
    }

    @Test
    override fun puzzleSolution1() {
        val expected = "2299996598890"
        val actual = testDay07.solution1(input)
        assertEquals(expected, actual, "🎅 Solution for Puzzle 1 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Puzzle 1 completed! $actual")
    }

    @Test
    fun puzzleSolution2Example() {
        val exampleInput =
            """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
            """.trimIndent()
        val expected = "11387"
        val actual = testDay07.solution2(exampleInput)
        assertEquals(expected, actual, "🎅 Solution for Example 2 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Puzzle 1 completed! $actual")
    }

    @Test
    override fun puzzleSolution2() {
        val expected = "362646859298554"
        val actual = testDay07.solution2(input)
        assertEquals(expected, actual, "🎅 Solution for Puzzle 2 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Puzzle 2 completed! $actual")
    }
}
