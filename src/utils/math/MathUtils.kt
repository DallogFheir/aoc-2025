package utils.math

import kotlin.math.pow

object MathUtils {
    fun getFirstNumberForDigitCount(digitCount: Int): Int {
        ensureDigitCountPositive(digitCount)

        if (digitCount == 1) {
            return 0
        }

        return 10.0.pow(digitCount - 1).toInt()
    }

    fun getLastNumberForDigitCount(digitCount: Int): Int {
        ensureDigitCountPositive(digitCount)

        return 10.0.pow(digitCount).toInt() - 1
    }

    private fun ensureDigitCountPositive(digitCount: Int) {
        if (digitCount <= 0) {
            throw IllegalArgumentException("Digit count must be positive, got $digitCount")
        }
    }
}
