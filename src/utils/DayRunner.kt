package utils

private const val MINIMUM_DAY_NUMBER = 0
private const val MAXIMUM_DAY_NUMBER = 12

class DayRunner<T, U>(
    private val dayNumber: Int,
    private val part1Solver: ((dayNumber: Int) -> T),
    private val part2Solver: ((dayNumber: Int) -> U)?,
) {
    init {
        require(dayNumber in MINIMUM_DAY_NUMBER..MAXIMUM_DAY_NUMBER)
    }

    fun run() {
        println("DAY $dayNumber")

        val part1Result = part1Solver(dayNumber)
        println("Part 1: $part1Result")

        if (part2Solver == null) {
            return
        }

        val part2Result = part2Solver(dayNumber)

        if (part2Result != Unit) {
            println("Part 2: $part2Result")
        }
    }
}
