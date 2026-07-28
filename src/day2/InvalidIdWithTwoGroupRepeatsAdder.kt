package day2

import day2.range.SameLengthRange
import utils.math.wrappers.PositiveIntegerWrapper

private const val groupRepeatCount = 2

class InvalidIdWithTwoGroupRepeatsAdder(range: SameLengthRange) : InvalidIdAdder(range) {

    override fun getGroupSizes(): List<Long> {
        if (!PositiveIntegerWrapper(digitCount.toLong()).isDivisibleBy(groupRepeatCount.toLong())) {
            return listOf()
        }

        return listOf((digitCount / groupRepeatCount).toLong())
    }
}
