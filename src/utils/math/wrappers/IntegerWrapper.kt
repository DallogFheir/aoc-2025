package utils.math.wrappers

import kotlin.math.abs
import kotlin.math.pow

open class IntegerWrapper(val number: Long) {
    val length: Int = abs(number).toString().length
    val firstDigit: Int = (abs(number) / (10.0.pow(length - 1).toLong())).toInt()

    fun isDivisibleBy(n: Long): Boolean {
        if (n == 0L) {
            throw IllegalArgumentException("Cannot divide by 0")
        }

        return number % n == 0L
    }

    open fun withAppendedDigit(digit: Int): IntegerWrapper {
        if (digit !in 0..9) {
            throw IllegalArgumentException("$digit is not a digit")
        }

        val signedDigit = if (number < 0) -digit else digit

        return IntegerWrapper(10 * number + signedDigit)
    }

    open fun toShiftedRight(): IntegerWrapper {
        val modulo = 10.0.pow(length - 1).toInt()

        return IntegerWrapper(number % modulo)
    }
}
