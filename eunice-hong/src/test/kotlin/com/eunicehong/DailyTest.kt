package com.eunicehong

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Common test code for puzzle solutions.
 */
abstract class DailyTest(
    day: Int,
) {
    protected val dayString = day.toString().padStart(2, '0')
    protected lateinit var input: String

    /**
     * Load puzzle input file to solve from resources
     */
    @BeforeEach
    fun loadPuzzle() {
        // Define the test file name
        val filePath = "/input/puzzle_$dayString.txt"
        // Load the file
        input = javaClass.getResource(filePath)?.readText() ?: ""
    }

    @Test
    abstract fun puzzleSolution1()

    @Test
    abstract fun puzzleSolution2()
}
