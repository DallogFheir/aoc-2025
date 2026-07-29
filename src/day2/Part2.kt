package day2

object Part2 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveWithInvalidIdAdder(dayNumber = dayNumber, fileName = fileName, adderFactory = { range ->
            InvalidIdAdder(range = range)
        })
    }
}
