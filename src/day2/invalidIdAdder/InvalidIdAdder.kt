package day2.invalidIdAdder

import utils.math.sequences.GeometricSequence
import utils.math.wrappers.PositiveIntegerWrapper
import utils.range.SameLengthRange
import kotlin.math.pow

open class InvalidIdAdder(range: SameLengthRange) {
    private val rangeStart = PositiveIntegerWrapper(range.start)
    private val rangeEnd = PositiveIntegerWrapper(range.end)
    private val alreadyProcessedGroupSizes = mutableSetOf<Long>()
    protected val digitCount = rangeStart.length


    fun sumUp(): Long {
        val groupSizes = getGroupSizes()

        return groupSizes.sumOf { sumUpForGroupSize(it.toInt()) }
    }

    protected open fun getGroupSizes(): List<Long> {
        return getFactorsOfNumberLength(rangeStart.number)
    }

    private fun getFactorsOfNumberLength(number: Long): List<Long> {
        val numberWrapper = PositiveIntegerWrapper(number)

        val rangeLengthFactors = PositiveIntegerWrapper(numberWrapper.length.toLong()).factorize()
        val rangeLengthFactorsWithoutNumberItself = rangeLengthFactors.dropLast(1)

        return rangeLengthFactorsWithoutNumberItself
    }

    private fun sumUpForGroupSize(groupSize: Int): Long {
        val rangeStartDigitGroups = rangeStart.divideDigitsIntoEqualGroups(groupSize)
        val rangeEndDigitGroups = rangeEnd.divideDigitsIntoEqualGroups(groupSize)

        val rangeStartFirstGroup = rangeStartDigitGroups.first()
        val rangeEndFirstGroup = rangeEndDigitGroups.first()

        val betweenCombinationsSum = sumUpForBetweenGroups(
            groupSize = groupSize,
            rangeStartGroup = rangeStartFirstGroup + 1,
            rangeEndGroup = rangeEndFirstGroup - 1
        )

        var result = betweenCombinationsSum

        val rangeStartRemainingDigitGroups = rangeStartDigitGroups.drop(1)
        val isRangeStartFirstGroupValid = isDigitGroupValid(
            remainingDigitGroups = rangeStartRemainingDigitGroups,
            firstDigitGroup = rangeStartFirstGroup,
            smallerToLargerDigitIndexComparator = { a, b -> a <= b })

        val rangeEndRemainingDigitGroups = rangeEndDigitGroups.drop(1)
        val isRangeEndFirstGroupValid = isDigitGroupValid(
            remainingDigitGroups = rangeEndRemainingDigitGroups,
            firstDigitGroup = rangeEndFirstGroup,
            smallerToLargerDigitIndexComparator = { a, b -> a >= b })

        if (rangeStartFirstGroup == rangeEndFirstGroup) {
            val areBothGroupsValid = isRangeStartFirstGroupValid && isRangeEndFirstGroupValid

            result += if (areBothGroupsValid && !isNumberRepeated(rangeStartFirstGroup)) constructNumberFromDigitGroup(
                digitGroup = rangeStartFirstGroup
            ) else 0
        } else {
            result += if (isRangeStartFirstGroupValid && !isNumberRepeated(rangeStartFirstGroup)) constructNumberFromDigitGroup(
                rangeStartFirstGroup
            ) else 0
            result += if (isRangeEndFirstGroupValid && !isNumberRepeated(rangeEndFirstGroup)) constructNumberFromDigitGroup(
                rangeEndFirstGroup
            ) else 0
        }

        alreadyProcessedGroupSizes.add(groupSize.toLong())

        return result
    }

    private fun sumUpForBetweenGroups(groupSize: Int, rangeStartGroup: Long, rangeEndGroup: Long): Long {
        if (rangeStartGroup > rangeEndGroup) {
            return 0L
        }

        var sum = 0L
        for (number in rangeStartGroup..rangeEndGroup) {
            if (!isNumberRepeated(number)) {
                sum += number
            }
        }

        val multiplierSequence = GeometricSequence(firstElement = 1.0, ratio = 10.0.pow(groupSize))
        val multiplier = multiplierSequence.getNFirstElementsSum(digitCount / groupSize)

        return (multiplier * sum).toLong()
    }

    private fun isDigitGroupValid(
        remainingDigitGroups: List<Long>,
        firstDigitGroup: Long,
        smallerToLargerDigitIndexComparator: (a: Long, b: Long) -> Boolean
    ): Boolean {
        val firstSmallerDigit = remainingDigitGroups.indexOfFirst { firstDigitGroup > it }
        val firstSmallerDigitIndex = if (firstSmallerDigit == -1) Long.MAX_VALUE else firstSmallerDigit.toLong()

        val firstLargerDigit = remainingDigitGroups.indexOfFirst { firstDigitGroup < it }
        val firstLargerDigitIndex = if (firstLargerDigit == -1) Long.MAX_VALUE else firstLargerDigit.toLong()

        return smallerToLargerDigitIndexComparator(firstSmallerDigitIndex, firstLargerDigitIndex)
    }

    private fun constructNumberFromDigitGroup(digitGroup: Long): Long {
        val digitGroupSize = PositiveIntegerWrapper(digitGroup).length

        val sequence = GeometricSequence(firstElement = 1.0, ratio = 10.0.pow(digitGroupSize))
        val multiplier = sequence.getNFirstElementsSum(digitCount / digitGroupSize)

        return (digitGroup * multiplier).toLong()
    }

    private fun isNumberRepeated(number: Long): Boolean {
        val numberWrapper = PositiveIntegerWrapper(number)

        val factors = getFactorsOfNumberLength(number)

        val isRepeated = factors.any { factor ->
            if (!alreadyProcessedGroupSizes.contains(factor)) {
                return@any false
            }

            val groups = numberWrapper.divideDigitsIntoEqualGroups(factor.toInt())
            val firstGroup = groups.first()

            groups.all { it == firstGroup }
        }

        return isRepeated
    }
}
