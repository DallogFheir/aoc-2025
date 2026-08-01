package day10.device

class JoltageCounters(val joltages: Array<Int>) {
    val size = joltages.size

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other !is JoltageCounters) {
            return false
        }

        return joltages.contentEquals(other.joltages)
    }

    override fun hashCode(): Int {
        return joltages.contentHashCode()
    }

    fun withAffectedIndices(vararg indices: Int): JoltageCounters {
        val newJoltages = joltages.copyOf()

        indices.forEach {
            require(it < joltages.size) { "Index $it out of range for joltage counters of size ${joltages.size}" }

            newJoltages[it] += 1
        }

        return JoltageCounters(joltages = newJoltages)
    }

    companion object {
        fun emptyOfLength(length: Int): JoltageCounters {
            return JoltageCounters(Array(length) { 0 })
        }
    }
}
