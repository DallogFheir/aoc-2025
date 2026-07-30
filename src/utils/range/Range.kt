package utils.range

import utils.math.MathUtils

private const val SEPARATOR = "-"
private const val ID_RANGE_PARTS_COUNT = 2

open class Range(val start: Long, val end: Long) {
    protected val startDigitCount: Int
    protected val endDigitCount: Int

    init {
        require(start > 0 && end > 0) { "Range ends must be positive" }
        require(start <= end) { "Range start must be <= end" }

        startDigitCount = start.toString().length
        endDigitCount = end.toString().length
    }

    fun divideIntoSameLengthSubranges(): List<SameLengthRange> {
        if (startDigitCount == endDigitCount) {
            return listOf(SameLengthRange(start = start, end = end))
        }

        val startSubrange =
            SameLengthRange(start = start, end = MathUtils.getLastNumberForDigitCount((startDigitCount)))
        val endSubrange =
            SameLengthRange(start = MathUtils.getFirstNumberForDigitCount(endDigitCount), end = end)

        val betweenSubranges = (startDigitCount + 1..<endDigitCount).map {
            SameLengthRange(
                start = MathUtils.getFirstNumberForDigitCount(it),
                end = MathUtils.getLastNumberForDigitCount(it)
            )
        }

        return buildList {
            add(startSubrange)
            addAll(betweenSubranges)
            add(endSubrange)
        }
    }

    fun contains(value: Long): Boolean {
        return value in start..end
    }

    companion object {
        fun fromString(string: String): Range {
            val parts = string.split(SEPARATOR)

            if (parts.size != ID_RANGE_PARTS_COUNT) {
                throw IllegalArgumentException("Invalid string for a range: $string")
            }

            val (start, end) = parts.map { it.toLong() }

            return Range(start = start, end = end)
        }
    }
}
