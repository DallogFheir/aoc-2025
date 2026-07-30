package utils.range.range

data class InvalidConstructorTestCase(
    val rangeStart: Long,
    val rangeEnd: Long,
)

data class FromStringTestCase(
    val string: String,
    val expectedRangeStart: Long,
    val expectedRangeEnd: Long,
)

data class InvalidFromStringTestCase(
    val string: String,
)

data class ToStringTestCase(
    val rangeStart: Long,
    val rangeEnd: Long,
    val expected: String,
)

data class SubrangeBoundary(
    val rangeStart: Long,
    val rangeEnd: Long,
)

data class DivideIntoSameLengthSubrangesTestCase(
    val rangeStart: Long,
    val rangeEnd: Long,
    val expectedSubrangeBoundaries: List<SubrangeBoundary>,
)

data class ContainsTestCase(
    val rangeStart: Long,
    val rangeEnd: Long,
    val valueToCheck: Long,
    val expected: Boolean,
)

data class CountInRangeTestCase(
    val rangeStart: Long,
    val rangeEnd: Long,
    val expected: Long,
)

data class CountOverlappingWithRangeTestCase(
    val range1Start: Long,
    val range1End: Long,
    val range2Start: Long,
    val range2End: Long,
    val expected: Long,
)

data class DoesOverlapWithRangeTestCase(
    val range1Start: Long,
    val range1End: Long,
    val range2Start: Long,
    val range2End: Long,
    val expected: Boolean,
)

data class MergeWithRangeTestCase(
    val range1Start: Long,
    val range1End: Long,
    val range2Start: Long,
    val range2End: Long,
    val expectedRangeStart: Long,
    val expectedRangeEnd: Long,
)

data class InvalidMergeWithRangeTestCase(
    val range1Start: Long,
    val range1End: Long,
    val range2Start: Long,
    val range2End: Long,
)
