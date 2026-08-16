package day12

import utils.DayRunner
import utils.Solver
import utils.TestCase

fun main() {
    DayRunner(
        dayNumber = 12,
        part1Solver = ::part1,
        part2Solver = {},
    ).run()
}

private fun part1(dayNumber: Int): Long {
    return Solver(
        dayNumber = dayNumber,
        testCases = listOf<TestCase<Long>>(),
        solver = Part1::solve,
    ).solve()
}
