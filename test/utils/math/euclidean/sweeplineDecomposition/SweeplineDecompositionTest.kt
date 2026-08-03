package utils.math.euclidean.sweeplineDecomposition

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.math.euclidean.Rectangle
import utils.math.euclidean.SweeplineDecomposition
import utils.math.euclidean.point.Point2d

class SweeplineDecompositionTest {
    companion object {
        @JvmStatic
        fun invalidEmptyListConstructorCases() = listOf(
            InvalidConstructorTestCase(
                coordinates = listOf()
            ),
        )

        @JvmStatic
        fun invalidNotClosedLoopConstructorCases() = listOf(
            InvalidConstructorTestCase(
                coordinates = listOf(
                    Point2d(x = 0.0, y = 0.0),
                    Point2d(x = 1.0, y = 1.0),
                    Point2d(x = 1.0, y = 2.0),
                )
            ),
            InvalidConstructorTestCase(
                coordinates = listOf(
                    Point2d(x = 0.0, y = 0.0),
                    Point2d(x = 0.0, y = 1.0),
                    Point2d(x = 2.0, y = 1.0),
                )
            ),
        )

        @JvmStatic
        fun sweepCases() = listOf(
            SweepTestCase(
                coordinates = listOf(
                    Point2d(x = 4.0, y = 0.0),
                    Point2d(x = 4.0, y = 0.0),
                    Point2d(x = 4.0, y = 1.0),
                    Point2d(x = 4.0, y = 2.0),
                    Point2d(x = 7.0, y = 2.0),
                    Point2d(x = 7.0, y = 0.0),
                    Point2d(x = 10.0, y = 0.0),
                    Point2d(x = 10.0, y = 4.0),
                    Point2d(x = 0.0, y = 4.0),
                    Point2d(x = 0.0, y = 0.0),
                ),
                expected = setOf(
                    Rectangle(topLeftCorner = Point2d(x = 0.0, y = 2.0), bottomRightCorner = Point2d(x = 4.0, y = 0.0)),
                    Rectangle(
                        topLeftCorner = Point2d(x = 7.0, y = 2.0),
                        bottomRightCorner = Point2d(x = 10.0, y = 0.0)
                    ),
                    Rectangle(
                        topLeftCorner = Point2d(x = 0.0, y = 4.0),
                        bottomRightCorner = Point2d(x = 10.0, y = 2.0)
                    ),
                )
            ),
            SweepTestCase(
                coordinates = listOf(
                    Point2d(x = 0.0, y = 0.0),
                    Point2d(x = 5.0, y = 0.0),
                    Point2d(x = 5.0, y = 2.0),
                    Point2d(x = 10.0, y = 2.0),
                    Point2d(x = 10.0, y = 5.0),
                    Point2d(x = 0.0, y = 5.0),
                ),
                expected = setOf(
                    Rectangle(topLeftCorner = Point2d(x = 0.0, y = 2.0), bottomRightCorner = Point2d(x = 5.0, y = 0.0)),
                    Rectangle(
                        topLeftCorner = Point2d(x = 0.0, y = 5.0),
                        bottomRightCorner = Point2d(x = 10.0, y = 2.0)
                    ),
                )
            ),
            SweepTestCase(
                coordinates = listOf(
                    Point2d(x = 3.0, y = 0.0),
                    Point2d(x = 7.0, y = 0.0),
                    Point2d(x = 7.0, y = 2.0),
                    Point2d(x = 10.0, y = 2.0),
                    Point2d(x = 10.0, y = 4.0),
                    Point2d(x = 7.0, y = 4.0),
                    Point2d(x = 7.0, y = 6.0),
                    Point2d(x = 3.0, y = 6.0),
                    Point2d(x = 3.0, y = 4.0),
                    Point2d(x = 0.0, y = 4.0),
                    Point2d(x = 0.0, y = 2.0),
                    Point2d(x = 3.0, y = 2.0),
                ),
                expected = setOf(
                    Rectangle(topLeftCorner = Point2d(x = 3.0, y = 2.0), bottomRightCorner = Point2d(x = 7.0, y = 0.0)),
                    Rectangle(
                        topLeftCorner = Point2d(x = 0.0, y = 4.0),
                        bottomRightCorner = Point2d(x = 10.0, y = 2.0)
                    ),
                    Rectangle(topLeftCorner = Point2d(x = 3.0, y = 6.0), bottomRightCorner = Point2d(x = 7.0, y = 4.0)),
                )
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("invalidEmptyListConstructorCases")
    fun `throws if trying to initialize with an empty list of coordinates`(case: InvalidConstructorTestCase) {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            SweeplineDecomposition(case.coordinates)
        }
    }

    @ParameterizedTest
    @MethodSource("invalidNotClosedLoopConstructorCases")
    fun `throws if trying to initialize with points that do not form a closed loop`(case: InvalidConstructorTestCase) {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            SweeplineDecomposition(case.coordinates)
        }
    }

    @ParameterizedTest
    @MethodSource("sweepCases")
    fun `sweeps correctly`(case: SweepTestCase) {
        val cut = SweeplineDecomposition(coordinates = case.coordinates)

        val result = cut.sweep()

        Assertions.assertEquals(case.expected, result)
    }
}
