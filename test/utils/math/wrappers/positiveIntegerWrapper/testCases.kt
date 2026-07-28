package utils.math.wrappers.positiveIntegerWrapper

data class InvalidConstructorTestCase(
    val number: Long,
)

data class FactorizeTestCase(
    val number: Long,
    val expected: List<Long>,
)

data class DivideDigitsIntoEqualGroupsTestCase(
    val number: Long,
    val groupSize: Int,
    val expected: List<Long>,
)

data class InvalidGroupSizeDivideDigitsIntoEqualGroupsTestCase(
    val groupSize: Int,
)

data class InvalidGroupSizeNotFactorDivideDigitsIntoEqualGroupsTestCase(
    val number: Long,
    val groupSize: Int,
)
