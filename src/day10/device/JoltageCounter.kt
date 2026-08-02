package day10.device

class JoltageCounter(val joltages: Array<Int>) {
    val size = joltages.size

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other !is JoltageCounter) {
            return false
        }

        return joltages.contentEquals(other.joltages)
    }

    override fun hashCode(): Int {
        return joltages.contentHashCode()
    }

    fun withAffectedIndices(vararg indices: Int, howManyTimes: Int): JoltageCounter {
        val newJoltages = joltages.copyOf()

        indices.forEach {
            require(it < joltages.size) { "Index $it out of range for joltage counters of size ${joltages.size}" }

            newJoltages[it] += howManyTimes
        }

        return JoltageCounter(joltages = newJoltages)
    }

    companion object {
        fun emptyOfLength(length: Int): JoltageCounter {
            return JoltageCounter(Array(length) { 0 })
        }
    }
}
