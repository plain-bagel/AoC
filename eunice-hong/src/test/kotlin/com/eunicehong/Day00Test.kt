package com.eunicehong

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Test for [Day00] as a sample
 */
internal class Day00Test : DailyTest(0) {
    private val testDay00: Day00 = Day00()

    @Test
    override fun puzzleSolution1() {
        val expected = "55538"
        val actual = testDay00.solution1(input)
        assertEquals(expected, actual, "Unexpected result for Day $dayString, Puzzle 1 Expected: $expected, actual: $actual")
        println("Day $dayString, Puzzle 1: $actual")
    }

    @Test
    override fun puzzleSolution2() {
        val expected = "54875"
        val actual = testDay00.solution2(input)
        assertEquals(expected, actual, "Unexpected result for Day $dayString, Puzzle 2 Expected: $expected, actual: $actual")
        println("Day $dayString, Puzzle 2: $actual")
    }
}
