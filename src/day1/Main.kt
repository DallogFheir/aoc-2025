package day1

import utils.DayRunner
import utils.Solver
import utils.TestCase

fun main() {
    DayRunner<Int, Nothing>(
        dayNumber = 1,
        part1Solver = ::part1,
        part2Solver = null,
    ).run()
}

private fun part1(): Int {
    val testCases = listOf(
        TestCase(fileName = "test-part1", expectedResult = 3),
    )

    return Solver(
        testCases = testCases,
        solver = Part1::solve
    ).solve()
}
