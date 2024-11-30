package com.eunicehong

/**
 * # 2023 Day 1: Trebuchet
 *
 * Copyright (c) Eric Wastl
 *
 * #### [Direct Link](https://adventofcode.com/2023/day/1)
 *
 */
internal class Day00 {
    /**
     * ## Part 1
     *
     * Something is wrong with global snow production, and you've been selected to take a look. The Elves have even given you a map; on it, they've used stars to mark the top fifty locations that are likely to be having problems.
     *
     * You've been doing this long enough to know that to restore snow operations, you need to check all fifty stars by December 25th.
     *
     * Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
     *
     * You try to ask why they can't just use a weather machine ("not powerful enough") and where they're even sending you ("the sky") and why your map looks mostly blank ("you sure ask a lot of questions") and hang on did you just say the sky ("of course, where do you think snow comes from") when you realize that the Elves are already loading you into a trebuchet ("please hold still, we need to strap you in").
     *
     * As they're making the final adjustments, they discover that their calibration document (your puzzle input) has been amended by a very young Elf who was apparently just excited to show off her art skills. Consequently, the Elves are having trouble reading the values on the document.
     *
     * The newly-improved calibration document consists of lines of text; each line originally contained a specific calibration value that the Elves now need to recover. On each line, the calibration value can be found by combining the first digit and the last digit (in that order) to form a single two-digit number.
     *
     * For example:
     *
     * ```text
     * 1abc2
     * pqr3stu8vwx
     * a1b2c3d4e5f
     * treb7uchet
     * ```
     *
     * In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces 142.
     *
     * Consider your entire calibration document. What is the sum of all of the calibration values?
     */
    fun solution1(puzzle: String): String {
        var sum = 0
        val lines = puzzle.lines().filter { it.isNotEmpty() }

        val regex = Regex("([123456789])")
        for (line in lines) {
            val matches = regex.findAll(line).toList()
            val tensKey = matches[0].value
            val onesKey = matches[matches.size - 1].value
            val tens = digits[tensKey] ?: 0
            val ones = digits[onesKey] ?: 0
            sum += tens * 10 + ones
        }

        return sum.toString()
    }

    /**
     * ## Part 2
     *
     * Your calculation isn't quite right. It looks like some of the digits are actually spelled out with letters: one, two, three, four, five, six, seven, eight, and nine also count as valid "digits".
     *
     * Equipped with this new information, you now need to find the real first and last digit on each line. For example:
     *
     * ```text
     * two1nine
     * eightwothree
     * abcone2threexyz
     * xtwone3four
     * 4nineeightseven2
     * zoneight234
     * 7pqrstsixteen
     * ```
     *
     * In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281.
     *
     * What is the sum of all of the calibration values?
     */
    fun solution2(puzzle: String): String =
        puzzle
            .lines() // Split into lines
            .filter { it.isNotEmpty() } // Remove empty lines
            .map { line ->
                var charStartIndex = 0
                var charEndIndex = line.length
                var tens: Int? = null
                var ones: Int? = null
                var head = line.substring(charStartIndex)
                var tail = line.substring(0, charEndIndex)

                do {
                    // If we found a digit at the start or end of the line,
                    // and we haven't already found a digit for that position,
                    // set the digit for that position.
                    for (digitString in digits.keys) {
                        if (head.startsWith(digitString) && tens == null) {
                            tens = digits[digitString]
                        }
                        if (tail.endsWith(digitString) && ones == null) {
                            ones = digits[digitString]
                        }
                    }

                    // If we didn't find a digit at the start or end of the line,
                    if (tens == null) {
                        charStartIndex++
                    }
                    if (ones == null) {
                        charEndIndex--
                    }
                    head = line.substring(charStartIndex)
                    tail = line.substring(0, charEndIndex)
                } while (head.isNotEmpty() &&
                    tail.isNotEmpty() &&
                    (tens == null || ones == null)
                )

                (tens ?: 0) * 10 + (ones ?: 0)
            }.fold(0) { sum, calibration -> sum + calibration }
            .toString()

    /**
     * Example digits map.
     */
    private val digits =
        mapOf(
            "1" to 1,
            "2" to 2,
            "3" to 3,
            "4" to 4,
            "5" to 5,
            "6" to 6,
            "7" to 7,
            "8" to 8,
            "9" to 9,
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9,
        )
}
