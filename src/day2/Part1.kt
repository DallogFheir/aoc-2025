package day2

object Part1 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveWithInvalidIdAdder(dayNumber = dayNumber, fileName = fileName, adderFactory = { range ->
            InvalidIdWithTwoGroupRepeatsAdder(range = range)
        })
    }
}
