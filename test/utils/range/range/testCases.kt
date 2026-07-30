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
