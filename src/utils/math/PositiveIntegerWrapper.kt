package utils.math

import kotlin.math.floor
import kotlin.math.sqrt

class PositiveIntegerWrapper(number: Int) : IntegerWrapper(number) {
    init {
        require(number > 0) { "Number must be positive, got $number" }
    }

    fun factorize(): List<Int> {
        val factors = mutableListOf<Int>()
        val lastPossibleFactor = floor(sqrt(number.toDouble())).toInt()

        (1..lastPossibleFactor).forEach { factor ->
            if (number % factor == 0) {
                factors.add(factor)

                val otherFactor = number / factor
                if (otherFactor != factor) {
                    factors.add(otherFactor)
                }
            }
        }

        return factors.sorted().toList()
    }

    override fun withAppendedDigit(digit: Int): PositiveIntegerWrapper {
        val result = super.withAppendedDigit(digit)

        return PositiveIntegerWrapper(result.number)
    }

    override fun toShiftedRight(): PositiveIntegerWrapper {
        val result = super.toShiftedRight()

        return PositiveIntegerWrapper(result.number)
    }
}
