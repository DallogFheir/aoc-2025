package day2

import day2.range.SameLengthRange
import utils.math.PositiveIntegerWrapper

class InvalidIdCounter(private val range: SameLengthRange) {
    private val rangeStart = PositiveIntegerWrapper(range.start)
    private val rangeEnd = PositiveIntegerWrapper(range.end)

    fun count(): Int {
        val rangeLengthFactors = rangeStart.factorize()

        return rangeLengthFactors.sumOf { calculateForGroupSize(it) }
    }

    private fun calculateForGroupSize(n: Int, builtGroup: PositiveIntegerWrapper? = null): Int {
        val startFirstDigit = rangeStart.firstDigit
        val endLastDigit = rangeEnd.firstDigit

        if (startFirstDigit == endLastDigit) {
            return calculateForGroupSize(
                n,
                builtGroup?.withAppendedDigit(startFirstDigit) ?: PositiveIntegerWrapper(startFirstDigit)
            )
        }

//        val digitEqualToStartFirstDigitCount = calculateCountForDigitComparedWithNumberByOperator(digit = startFirstDigit, number = rangeStart.toShiftedRight(), operator = { a, b -> a < b })

        return 0
    }

    private fun calculateCountForDigitComparedWithNumberByOperator(digit: Int, number: PositiveIntegerWrapper, operator: (Int, Int) -> Boolean): Int {
        return 0
    }
}
