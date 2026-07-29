package utils.listUtils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.product

class ListUtilsTest {
    companion object {
        @JvmStatic
        fun productCases() = listOf(
            ProductTestCase(
                list1 = listOf(1),
                list2 = listOf(),
                expected = listOf(),
            ),
            ProductTestCase(
                list1 = listOf(1),
                list2 = listOf(2),
                expected = listOf(1 to 2),
            ),
            ProductTestCase(
                list1 = listOf(1, 2),
                list2 = listOf(3, 4),
                expected = listOf(1 to 3, 1 to 4, 2 to 3, 2 to 4),
            )
        )
    }

    @ParameterizedTest
    @MethodSource("productCases")
    fun `returns product of lists correctly`(case: ProductTestCase<Int>) {
        val result = product(case.list1, case.list2)

        assertEquals(case.expected, result, "product should return ${case.expected}, got $result")
    }
}
