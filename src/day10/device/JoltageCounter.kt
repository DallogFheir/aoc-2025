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
}
