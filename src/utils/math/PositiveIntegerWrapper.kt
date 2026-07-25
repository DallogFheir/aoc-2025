package utils.math

import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt

class PositiveIntegerWrapper(number: Int) : IntegerWrapper(number) {
    init {
        require(number > 0) { "Number must be positive, got $number" }
    }

    override fun withAppendedDigit(digit: Int): PositiveIntegerWrapper {
        val result = super.withAppendedDigit(digit)

        return PositiveIntegerWrapper(result.number)
    }

    override fun toShiftedRight(): PositiveIntegerWrapper {
        val result = super.toShiftedRight()

        return PositiveIntegerWrapper(result.number)
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

    fun divideDigitsIntoEqualGroups(groupSize: Int): List<Int> {
        if (groupSize <= 0) {
            throw IllegalArgumentException("Group size must be positive, got $groupSize")
        }
        if (length % groupSize != 0) {
            throw IllegalArgumentException("Group size $groupSize must be a factor of digit count $length, got $groupSize")
        }

        val result = mutableListOf<Int>()
        val divisor = 10.0.pow(groupSize).toInt()

        var n = number
        while (n > 0) {
            result.add(n % divisor)

            n /= divisor
        }

        return result.reversed()
    }
}
