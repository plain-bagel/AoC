package com.eunicehong

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Check if the solutions for [Day09] is correct.
 */
internal class Day09Test : DailyTest(9) {
    private val testDay09: Day09 = Day09()

    @Test
    fun puzzleSolution1Example() {
        val exampleInput = "2333133121414131402"
        val expected = "1928"
        val actual = testDay09.solution1(exampleInput)
        assertEquals(expected, actual, "🎅 Solution for Example 1 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Example 1 completed! $actual")
    }

    @Test
    override fun puzzleSolution1() {
        val expected = "Not implemented"
        val actual = testDay09.solution1(input)
        assertEquals(expected, actual, "🎅 Solution for Puzzle 1 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Puzzle 1 completed! $actual")
    }

    @Test
    fun puzzleSolution2Example() {
        val exampleInput = "2333133121414131402"
        val expected = "2858"
        val actual = testDay09.solution2(exampleInput)
        assertEquals(expected, actual, "🎅 Solution 2 for Example 1 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Example 1 completed! $actual")
    }

    @Test
    override fun puzzleSolution2() {
        val expected = "Not implemented"
        val actual = testDay09.solution2(input)
        assertEquals(expected, actual, "🎅 Solution for Puzzle 2 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Puzzle 2 completed! $actual")
    }
}
