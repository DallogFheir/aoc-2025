//package day2
//
//import day2.range.SameLengthRange
//import utils.math.wrappers.PositiveIntegerWrapper
//
//class InvalidIdCounter(range: SameLengthRange) {
//    private val groupSizeToCombinationsCount = mutableMapOf<Int, Int>()
//    private val rangeStart = PositiveIntegerWrapper(range.start)
//    private val rangeEnd = PositiveIntegerWrapper(range.end)
//
//    fun count(): Int {
//        val rangeLengthFactors = PositiveIntegerWrapper(rangeStart.length).factorize()
//        val rangeLengthFactorsWithoutNumberItself = rangeLengthFactors.dropLast(1)
//
//        return rangeLengthFactorsWithoutNumberItself.sumOf { calculateForGroupSize(it) }
//    }
//
//    private fun calculateForGroupSize(groupSize: Int): Int {
//        val rangeStartDigitGroups = rangeStart.divideDigitsIntoEqualGroups(groupSize)
//        val rangeEndDigitGroups = rangeEnd.divideDigitsIntoEqualGroups(groupSize)
//
//        val rangeStartFirstGroup = rangeStartDigitGroups.first()
//        val rangeEndFirstGroup = rangeEndDigitGroups.first()
//
//        val betweenCombinationsCount = rangeEndFirstGroup - rangeStartFirstGroup
//
//        val rangeStartRemainingDigitGroups = rangeStartDigitGroups.drop(1)
//        val rangeEndRemainingDigitGroups = rangeEndDigitGroups.drop(1)
//
//        val isRangeStartFirstGroupValid =
//            rangeStartRemainingDigitGroups.any { rangeStartFirstGroup > it } || rangeStartRemainingDigitGroups.all { rangeStartFirstGroup == it }
//        val isRangeEndFirstGroupValid =
//            rangeEndRemainingDigitGroups.any { rangeEndFirstGroup > it } || rangeEndRemainingDigitGroups.all { rangeEndFirstGroup == it }
//        val areBothFirstGroupsValid = isRangeStartFirstGroupValid && isRangeEndFirstGroupValid
//
//        val rangeBoundaryCombinationsCount = if (rangeStartFirstGroup == rangeEndFirstGroup) {
//            if (areBothFirstGroupsValid) 1 else 0
//        } else {
//            (if (isRangeStartFirstGroupValid) 1 else 0) + (if (isRangeEndFirstGroupValid) 1 else 0)
//        }
//
//        val combinationsCount = betweenCombinationsCount + rangeBoundaryCombinationsCount
//
//        val repeatedCombinationsCount = getRepetitionCountForGroupSize(groupSize)
//
//        val result = combinationsCount - repeatedCombinationsCount
//
//        groupSizeToCombinationsCount[groupSize] = result
//
//        return result
//    }
//
//    private fun getRepetitionCountForGroupSize(groupSize: Int): Int {
//        return groupSizeToCombinationsCount.map {
//            val (previousGroupSize, count) = it
//
//            if (groupSize % previousGroupSize == 0) {
//                return@map count
//            }
//
//            0
//        }.sum()
//    }
//}
