package day2

import day2.invalidIdAdder.InvalidIdWithTwoGroupRepeatsAdder

object Part1 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveWithInvalidIdAdder(dayNumber = dayNumber, fileName = fileName, adderFactory = { range ->
            InvalidIdWithTwoGroupRepeatsAdder(range = range)
        })
    }
}
