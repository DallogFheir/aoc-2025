package day2

import day2.invalidIdAdder.InvalidIdAdder

object Part2 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveWithInvalidIdAdder(dayNumber = dayNumber, fileName = fileName, adderFactory = { range ->
            InvalidIdAdder(range = range)
        })
    }
}
