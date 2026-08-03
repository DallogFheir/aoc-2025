package day02

import day02.invalidIdAdder.InvalidIdAdder

object Part2 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveWithInvalidIdAdder(dayNumber = dayNumber, fileName = fileName, adderFactory = { range ->
            InvalidIdAdder(range = range)
        })
    }
}
