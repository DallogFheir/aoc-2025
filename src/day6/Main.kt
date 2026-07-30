package day6

import utils.DayRunner
import utils.Solver
import utils.TestCase

fun main() {
    DayRunner(
        dayNumber = 6,
        part1Solver = ::part1,
        part2Solver = ::part2,
    ).run()
}

private fun part1(dayNumber: Int): Long {
    val testCases = listOf(
        TestCase(fileName = "test", expectedResult = 4277556L),
    )

    return Solver(
        dayNumber = dayNumber,
        testCases = testCases,
        solver = Part1::solve
    ).solve()
}

private fun part2(dayNumber: Int): Long {
    val testCases = listOf(
        TestCase(fileName = "test", expectedResult = 14L),
    )

    return Solver(
        dayNumber = dayNumber,
        testCases = testCases,
        solver = Part2::solve
    ).solve()
}
