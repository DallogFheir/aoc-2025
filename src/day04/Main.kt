package day04

import utils.DayRunner
import utils.Solver
import utils.TestCase

fun main() {
    DayRunner(
        dayNumber = 4,
        part1Solver = ::part1,
        part2Solver = ::part2,
    ).run()
}

private fun part1(dayNumber: Int): Long {
    val testCases = listOf(
        TestCase(fileName = "test", expectedResult = 13L),
    )

    return Solver(
        dayNumber = dayNumber,
        testCases = testCases,
        solver = Part1::solve
    ).solve()
}

private fun part2(dayNumber: Int): Long {
    val testCases = listOf(
        TestCase(fileName = "test", expectedResult = 43L),
    )

    return Solver(
        dayNumber = dayNumber,
        testCases = testCases,
        solver = Part2::solve
    ).solve()
}
