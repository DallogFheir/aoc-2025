package day2

import day2.range.SameLengthRange
import utils.math.sequences.ArithmeticSequence
import utils.math.sequences.GeometricSequence
import utils.math.wrappers.PositiveIntegerWrapper
import kotlin.math.pow

class InvalidIdAdder(range: SameLengthRange) {
    private val rangeStart = PositiveIntegerWrapper(range.start)
    private val rangeEnd = PositiveIntegerWrapper(range.end)
    private val digitCount = rangeStart.length

    fun sumUp(): Long {
        val rangeLengthFactors = PositiveIntegerWrapper(digitCount.toLong()).factorize()
        val rangeLengthFactorsWithoutNumberItself = rangeLengthFactors.dropLast(1)

        return rangeLengthFactorsWithoutNumberItself.sumOf { sumUpForGroupSize(it.toInt()) }
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

        val rangeStartRemainingDigitGroups = rangeStartDigitGroups.drop(1)
        val rangeEndRemainingDigitGroups = rangeEndDigitGroups.drop(1)

        var result = betweenCombinationsSum

        val firstSmallerDigit = rangeStartRemainingDigitGroups.indexOfFirst { rangeStartFirstGroup > it }
        val firstLargerDigit = rangeStartRemainingDigitGroups.indexOfFirst { rangeStartFirstGroup < it }
        val firstSmallerIdx = if (firstSmallerDigit == -1) Long.MAX_VALUE else firstSmallerDigit.toLong()
        val firstLargerIdx = if (firstLargerDigit == -1) Long.MAX_VALUE else firstLargerDigit.toLong()
        val isRangeStartFirstGroupValid = firstSmallerIdx <= firstLargerIdx

        val firstSmallerDigit2 = rangeEndRemainingDigitGroups.indexOfFirst { rangeEndFirstGroup > it }
        val firstLargerDigit2 = rangeEndRemainingDigitGroups.indexOfFirst { rangeEndFirstGroup < it }
        val firstSmallerIdx2 = if (firstSmallerDigit2 == -1) Long.MAX_VALUE else firstSmallerDigit2.toLong()
        val firstLargerIdx2 = if (firstLargerDigit2 == -1) Long.MAX_VALUE else firstLargerDigit2.toLong()
        val isRangeEndFirstGroupValid = firstLargerIdx2 <= firstSmallerIdx2

        if (rangeStartFirstGroup == rangeEndFirstGroup) {
            val areBothGroupsValid = isRangeStartFirstGroupValid && isRangeEndFirstGroupValid

            result += if (areBothGroupsValid && !isNumRepeated(rangeStartFirstGroup)) constructNumberFromDigitGroup(digitGroup = rangeStartFirstGroup) else 0
        } else {
            result += if (isRangeStartFirstGroupValid && !isNumRepeated(rangeStartFirstGroup)) constructNumberFromDigitGroup(rangeStartFirstGroup) else 0
            result += if (isRangeEndFirstGroupValid && !isNumRepeated(rangeEndFirstGroup)) constructNumberFromDigitGroup(rangeEndFirstGroup) else 0
        }

        return result
    }

    private fun sumUpForBetweenGroups(groupSize: Int, rangeStartGroup: Long, rangeEndGroup: Long): Long {
        if (rangeStartGroup > rangeEndGroup) {
            return 0L
        }

        var sum = 0L
        for (i in rangeStartGroup..rangeEndGroup) {
            val isRepeated = isNumRepeated(i)

            if (!isRepeated) {
                sum += i
            }
        }

//        val betweenGroupsSequence = ArithmeticSequence(firstElement = rangeStartGroup.toDouble(), difference = 1.0)
//        val lastElementIndex = betweenGroupsSequence.getIndexOfElement(rangeEndGroup.toDouble())
//        val sum = betweenGroupsSequence.getNFirstElementsSum(lastElementIndex)

        val multiplierSequence = GeometricSequence(firstElement = 1.0, ratio = 10.0.pow(groupSize))
        val multiplier = multiplierSequence.getNFirstElementsSum(digitCount / groupSize)

        return (multiplier * sum).toLong()
    }

    private fun constructNumberFromDigitGroup(digitGroup: Long): Long {
        val digitGroupSize = PositiveIntegerWrapper(digitGroup).length

        val sequence = GeometricSequence(firstElement = 1.0, ratio = 10.0.pow(digitGroupSize))
        val multiplier = sequence.getNFirstElementsSum(digitCount / digitGroupSize)

        return (digitGroup * multiplier).toLong()
    }

    private fun isNumRepeated(num: Long): Boolean {
        val w = PositiveIntegerWrapper(num)
        val facss = PositiveIntegerWrapper(w.length.toLong()).factorize()
        val facs = facss.dropLast(1)

        val isRepeated = facs.any { factor ->
            val groups = w.divideDigitsIntoEqualGroups(factor.toInt())
            val firstGroup = groups.first()

            groups.all { it == firstGroup }
        }

        return isRepeated
    }
}
