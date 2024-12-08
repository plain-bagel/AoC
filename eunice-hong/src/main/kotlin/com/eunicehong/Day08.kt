package com.eunicehong

/**
 * # 2024 Day 8: Resonant Collinearity
 *
 * Copyright (c) Eric Wastl
 *
 * #### [Direct Link](https://adventofcode.com/2024/day/8)
 *
 */
internal class Day08 {
    /**
     * ## Part 1
     *
     * You find yourselves on the roof of a top-secret Easter Bunny installation.
     *
     * While The Historians do their thing, you take a look at the familiar huge antenna. Much to your surprise, it seems to have been reconfigured to emit a signal that makes people 0.1% more likely to buy Easter Bunny brand Imitation Mediocre Chocolate as a Christmas gift! Unthinkable!
     *
     * Scanning across the city, you find that there are actually many such antennas. Each antenna is tuned to a specific frequency indicated by a single lowercase letter, uppercase letter, or digit. You create a map (your puzzle input) of these antennas. For example:
     *
     * ```
     * ............
     * ........0...
     * .....0......
     * .......0....
     * ....0.......
     * ......A.....
     * ............
     * ............
     * ........A...
     * .........A..
     * ............
     * ............
     * ```
     *
     * The signal only applies its nefarious effect at specific antinodes based on the resonant frequencies of the antennas. In particular, an antinode occurs at any point that is perfectly in line with two antennas of the same frequency - but only when one of the antennas is twice as far away as the other. This means that for any pair of antennas with the same frequency, there are two antinodes, one on either side of them.
     *
     * So, for these two antennas with frequency a, they create the two antinodes marked with #:
     *
     * ```
     * ..........
     * ...#......
     * ..........
     * ....a.....
     * ..........
     * .....a....
     * ..........
     * ......#...
     * ..........
     * ..........
     * ```
     *
     * Adding a third antenna with the same frequency creates several more antinodes. It would ideally add four antinodes, but two are off the right side of the map, so instead it adds only two:
     *
     * ```
     * ..........
     * ...#......
     * #.........
     * ....a.....
     * ........a.
     * .....a....
     * ..#.......
     * ......#...
     * ..........
     * ..........
     * ```
     * Antennas with different frequencies don't create antinodes; A and a count as different frequencies. However, antinodes can occur at locations that contain antennas. In this diagram, the lone antenna with frequency capital A creates no antinodes but has a lowercase-a-frequency antinode at its location:
     *
     * ```
     * ..........
     * ...#......
     * #.........
     * ....a.....
     * ........a.
     * .....a....
     * ..#.......
     * ......A...
     * ..........
     * ..........
     * ```
     * The first example has antennas with two different frequencies, so the antinodes they create look like this, plus an antinode overlapping the topmost A-frequency antenna:
     *
     * ```
     * ......#....#
     * ...#....0...
     * ....#0....#.
     * ..#....0....
     * ....0....#..
     * .#....A.....
     * ...#........
     * #......#....
     * ........A...
     * .........A..
     * ..........#.
     * ..........#.
     * ```
     *
     * Because the topmost A-frequency antenna overlaps with a 0-frequency antinode, there are 14 total unique locations that contain an antinode within the bounds of the map.
     *
     * Calculate the impact of the signal. How many unique locations within the bounds of the map contain an antinode?
     */
    fun solution1(puzzle: String): String {
        val lines = puzzle.lines().filter { it.isNotEmpty() }
        val antennas = lines.getAntennas()
        return antennas
            .asSequence()
            .flatMap { antenna -> antenna.getAntiNodes(antennas) }
            .filter { antiNode -> lines.isValid(antiNode) }
            .distinct()
            .count()
            .toString()
    }

    /**
     * ## Part 2
     *
     * Watching over your shoulder as you work, one of The Historians asks if you took the effects of resonant harmonics into your calculations.
     *
     * Whoops!
     *
     * After updating your model, it turns out that an antinode occurs at any grid position exactly in line with at least two antennas of the same frequency, regardless of distance. This means that some of the new antinodes will occur at the position of each antenna (unless that antenna is the only one of its frequency).
     *
     * So, these three T-frequency antennas now create many antinodes:
     *
     * ```
     * T....#....
     * ...T......
     * .T....#...
     * .........#
     * ..#.......
     * ..........
     * ...#......
     * ..........
     * ....#.....
     * ..........
     * ```
     * In fact, the three T-frequency antennas are all exactly in line with two antennas, so they are all also antinodes! This brings the total number of antinodes in the above example to 9.
     *
     * The original example now has 34 antinodes, including the antinodes that appear on every antenna:
     *
     * ```
     * ##....#....#
     * .#.#....0...
     * ..#.#0....#.
     * ..##...0....
     * ....0....#..
     * .#...#A....#
     * ...#..#.....
     * #....#.#....
     * ..#.....A...
     * ....#....A..
     * .#........#.
     * ...#......##
     * ```
     * Calculate the impact of the signal using this updated model. How many unique locations within the bounds of the map contain an antinode?
     */
    fun solution2(puzzle: String): String {
        val lines = puzzle.lines().filter { it.isNotEmpty() }
        return ""
    }

    private fun List<String>.getAntennas(): List<Antenna> =
        (0 until size * get(0).length).mapNotNull {
            val x = it % size
            val y = it / size
            when (get(y)[x]) {
                in 'A'..'Z',
                in 'a'..'z',
                in '0'..'9',
                -> Antenna(Pair(x, y), get(y)[x])
                else -> null
            }
        }

    private fun List<String>.isValid(antiNode: AntiNode) = antiNode.x in 0 until get(0).length && antiNode.y in indices

    private data class AntiNode(
        private val position: Pair<Int, Int>,
        val frequency: Char,
    ) : Object(position) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is AntiNode) return false
            return x == other.x && y == other.y
        }

        override fun hashCode(): Int {
            var result = x
            result = 32 * result + y
            return result
        }
    }

    private data class Antenna(
        private val position: Pair<Int, Int>,
        val frequency: Char,
    ) : Object(position) {
        fun getAntiNodes(antennas: List<Antenna>): List<AntiNode> =
            antennas
                .filter { antenna -> antenna.frequency == frequency && antenna != this }
                .flatMap { antenna ->
                    listOf(
                        AntiNode(Pair(2 * x - antenna.x, 2 * y - antenna.y), frequency),
                        AntiNode(Pair(2 * antenna.x - x, 2 * antenna.y - y), frequency),
                    )
                }
    }

    private abstract class Object(
        position: Pair<Int, Int>,
    ) {
        val x: Int = position.first
        val y: Int = position.second
    }
}
