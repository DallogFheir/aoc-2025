package day2

import utils.math.wrappers.PositiveIntegerWrapper
import utils.range.SameLengthRange

private const val GROUP_REPEAT_COUNT = 2

class InvalidIdWithTwoGroupRepeatsAdder(range: SameLengthRange) : InvalidIdAdder(range) {

    override fun getGroupSizes(): List<Long> {
        if (!PositiveIntegerWrapper(digitCount.toLong()).isDivisibleBy(GROUP_REPEAT_COUNT.toLong())) {
            return listOf()
        }

        return listOf((digitCount / GROUP_REPEAT_COUNT).toLong())
    }
}
