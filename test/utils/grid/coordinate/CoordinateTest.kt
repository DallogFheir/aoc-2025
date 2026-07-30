package utils.grid.coordinate

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.grid.Coordinate

class CoordinateTest {
    companion object {
        @JvmStatic
        fun invalidConstructorCases() = listOf(
            InvalidConstructorTestCase(x = -1, y = 0),
            InvalidConstructorTestCase(x = 0, y = -1),
        )

        @JvmStatic
        fun isValidCases() = listOf(
            IsValidTestCase(x = 0, y = 0, expected = true),
            IsValidTestCase(x = 1, y = 1, expected = true),
            IsValidTestCase(x = -1, y = 0, expected = false),
            IsValidTestCase(x = 0, y = -1, expected = false),
        )
    }

    @ParameterizedTest
    @MethodSource("invalidConstructorCases")
    fun `throws if trying to initialize with invalid coordinate values`(case: InvalidConstructorTestCase) {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Coordinate(x = case.x, y = case.y)
        }
    }

    @ParameterizedTest
    @MethodSource("isValidCases")
    fun `returns whether coordinate values are valid`(case: IsValidTestCase) {
        val result = Coordinate.isValid(x = case.x, y = case.y)

        Assertions.assertEquals(
            case.expected,
            result,
            "isValid for coordinate values x=${case.x} and y=${case.y} should return ${case.expected}, got $result"
        )
    }
}
