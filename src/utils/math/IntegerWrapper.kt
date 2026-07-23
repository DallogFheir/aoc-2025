package utils.math

import kotlin.math.abs
import kotlin.math.pow

open class IntegerWrapper(protected val number: Int) {
    private val digitCount = abs(number).toString().length

    fun getFirstDigit(): Int {
        return abs(number) / (10.0.pow(digitCount - 1).toInt())
    }

    fun isDivisibleBy(n: Int): Boolean {
        if (n == 0) {
            throw IllegalArgumentException("Cannot divide by 0")
        }

        return number % n == 0
    }
}
