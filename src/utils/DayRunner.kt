package utils

class DayRunner<T, U>(
    private val dayNumber: Int,
    private val part1Solver: (() -> T),
    private val part2Solver: (() -> U)?,
) {
    init {
        require(dayNumber in MINIMUM_DAY_NUMBER..MAXIMUM_DAY_NUMBER)
    }

    fun run() {
        println("DAY $dayNumber")

        val part1Result = part1Solver()
        println("Part 1: $part1Result")

        if (part2Solver == null) {
            return
        }

        val part2Result = part2Solver()
        println("Part 2: $part2Result")
    }

    companion object {
        const val MINIMUM_DAY_NUMBER = 0
        const val MAXIMUM_DAY_NUMBER = 12
    }
}
