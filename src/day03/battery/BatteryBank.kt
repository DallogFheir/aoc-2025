package day03.battery

private data class JoltageWithIndex(
    val joltage: Int,
    val index: Int,
)

private data class AllowedIndices(
    val minimum: Int,
    val maximum: Int,
)

class BatteryBank(private val activatableBatteryCount: Int, private val joltages: List<Int>) {
    init {
        require(activatableBatteryCount in 1..joltages.size) {
            "activatable battery count must be between 1 and battery count ${joltages.size}, got $activatableBatteryCount"
        }
    }

    fun findLargestPossibleJoltage(): Long {
        var minimumAllowedIndex = 0

        val largestJoltages = (activatableBatteryCount downTo 1).map {
            val maximumAllowedIndex = joltages.lastIndex - (it - 1)

            val largestJoltageWithIndex = findLargestJoltageWithAllowedIndices(
                AllowedIndices(
                    minimum = minimumAllowedIndex,
                    maximum = maximumAllowedIndex,
                )
            )

            minimumAllowedIndex = largestJoltageWithIndex.index + 1

            largestJoltageWithIndex.joltage
        }

        return buildTotalJoltage(largestJoltages)
    }

    private fun findLargestJoltageWithAllowedIndices(allowedIndices: AllowedIndices): JoltageWithIndex {
        val joltagesToSearch = joltages.slice(allowedIndices.minimum..allowedIndices.maximum)

        val largestJoltageWithIndex = joltagesToSearch.foldIndexed(
            initial = JoltageWithIndex(
                joltage = 0,
                index = -1,
            )
        ) { slicedIndex, largestJoltageWithIndex, joltage ->
            val index = slicedIndex + allowedIndices.minimum

            if (joltage > largestJoltageWithIndex.joltage) {
                return@foldIndexed JoltageWithIndex(joltage = joltage, index = index)
            }

            largestJoltageWithIndex
        }

        if (largestJoltageWithIndex.index == -1) {
            return JoltageWithIndex(
                joltage = 0,
                index = 0,
            )
        }

        return largestJoltageWithIndex
    }

    private fun buildTotalJoltage(largestJoltages: List<Int>): Long {
        return largestJoltages.fold(0L) { total, joltage ->
            10L * total + joltage.toLong()
        }
    }

    companion object {
        fun fromString(string: String, activatableBatteryCount: Int): BatteryBank {
            val joltages = string.map {
                val digit = it.digitToIntOrNull() ?: throw IllegalArgumentException("Invalid input $string")

                digit
            }

            return BatteryBank(joltages = joltages, activatableBatteryCount = activatableBatteryCount)
        }
    }
}
