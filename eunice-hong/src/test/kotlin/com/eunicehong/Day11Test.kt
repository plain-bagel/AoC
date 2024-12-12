package com.eunicehong

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Check if the solutions for [Day11] is correct.
 */
internal class Day11Test : DailyTest(11) {
    private val testDay11: Day11 = Day11()

    @Test
     fun puzzleSolution1Example() {
         val exampleInput = "125 17"
        val expected = "55312"
        val actual = testDay11.solution1(exampleInput)
        assertEquals(expected, actual, "🎅 Solution for Puzzle 1 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Puzzle 1 completed! $actual")
    }
    @Test
    override fun puzzleSolution1() {
        val expected = "228668"
        val actual = testDay11.solution1(input)
        assertEquals(expected, actual, "🎅 Solution for Puzzle 1 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Puzzle 1 completed! $actual")
    }

    @Test
    override fun puzzleSolution2() {
        val expected = "270673834779359"
        val actual = testDay11.solution2(input)
        assertEquals(expected, actual, "🎅 Solution for Puzzle 2 Day $dayString has not been implemented yet.")
        println("🌲 Day $dayString Puzzle 2 completed! $actual")
    }
}
