package utils.math.euclidean.point.point2d

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.math.euclidean.point.Point2d

class Point2dTest {
    companion object {
        @JvmStatic
        fun coordinateCases() = listOf(
            CoordinateTestCase(
                x = 0.0,
                y = 0.0,
            ),
            CoordinateTestCase(
                x = 1.0,
                y = 2.0,
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("coordinateCases")
    fun `coordinates are available at fields x, y`(case: CoordinateTestCase) {
        val cut = Point2d(x = case.x, y = case.y)

        Assertions.assertEquals(case.x, cut.x)
        Assertions.assertEquals(case.y, cut.y)
    }
}
