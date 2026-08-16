package utils.range

import utils.math.MathUtils
import kotlin.math.max
import kotlin.math.min

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

    override fun toString(): String {
        return "[$start; $end]"
    }

    operator fun contains(value: Long): Boolean {
        return value in start..end
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

    fun countInRange(): Long {
        return end - start + 1
    }

    fun countOverlappingWithRange(other: Range): Long {
        if (!doesOverlapWithRange(other)) {
            return 0L
        }

        val overlappingRangeStart = max(start, other.start)
        val overlappingRangeEnd = min(end, other.end)

        return Range(start = overlappingRangeStart, end = overlappingRangeEnd).countInRange()
    }

    fun doesOverlapWithRange(other: Range): Boolean {
        return start <= other.end && end >= other.start
    }

    fun mergeWithRange(other: Range): Range {
        if (!doesOverlapWithRange(other)) {
            throw IllegalArgumentException("Cannot merge ranges that do not overlap, got $this and $other")
        }

        val mergedRangeStart = min(start, other.start)
        val mergedRangeEnd = max(end, other.end)

        return Range(start = mergedRangeStart, end = mergedRangeEnd)
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
