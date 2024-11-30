package com.eunicehong

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Check if the solutions for [Day17] is correct.
 */
internal class Day17Test : DailyTest(17) {
    private val testDay17: Day17 = Day17()

    @Test
    override fun puzzleSolution1() {
        val expected = "Not implemented"
        val actual = testDay17.solution1(input)
        assertEquals(expected, actual, "🎅 Solution for Puzzle 1 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Puzzle 1 completed! $actual")
    }

    @Test
    override fun puzzleSolution2() {
        val expected = "Not implemented"
        val actual = testDay17.solution2(input)
        assertEquals(expected, actual, "🎅 Solution for Puzzle 2 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Puzzle 2 completed! $actual")
    }
}
