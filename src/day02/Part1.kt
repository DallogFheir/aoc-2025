package day02

import day02.invalidIdAdder.InvalidIdWithTwoGroupRepeatsAdder

object Part1 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveWithInvalidIdAdder(dayNumber = dayNumber, fileName = fileName, adderFactory = { range ->
            InvalidIdWithTwoGroupRepeatsAdder(range = range)
        })
    }
}
