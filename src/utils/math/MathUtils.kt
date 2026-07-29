package utils.math

import kotlin.math.pow

object MathUtils {
    fun getFirstNumberForDigitCount(digitCount: Int): Long {
        ensureDigitCountPositive(digitCount)

        if (digitCount == 1) {
            return 0
        }

        return 10.0.pow(digitCount - 1).toLong()
    }

    fun getLastNumberForDigitCount(digitCount: Int): Long {
        ensureDigitCountPositive(digitCount)

        return 10.0.pow(digitCount).toLong() - 1
    }

    private fun ensureDigitCountPositive(digitCount: Int) {
        if (digitCount <= 0) {
            throw IllegalArgumentException("Digit count must be positive, got $digitCount")
        }
    }
}
