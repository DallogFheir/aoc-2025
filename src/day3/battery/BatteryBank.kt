package day3.battery

private data class JoltageWithIndex(
    val joltage: Int,
    val index: Int,
)

private data class AllowedIndices(
    val minimum: Int,
    val maximum: Int,
)

class BatteryBank(private val activatableBatteryCount: Int, private val joltages: List<Int>) {
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
        var largestJoltageWithIndex: JoltageWithIndex?
        var joltagesToSearch = joltages.slice(allowedIndices.minimum..joltages.lastIndex)

        do {
            largestJoltageWithIndex = joltagesToSearch.foldIndexed(
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

            joltagesToSearch = joltages.slice(allowedIndices.minimum..<largestJoltageWithIndex.index)
        } while (largestJoltageWithIndex.index > allowedIndices.maximum)

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
