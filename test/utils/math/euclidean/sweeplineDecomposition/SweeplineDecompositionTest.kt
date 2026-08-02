package utils.math.euclidean.sweeplineDecomposition

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.math.euclidean.Point
import utils.math.euclidean.Rectangle
import utils.math.euclidean.SweeplineDecomposition

class SweeplineDecompositionTest {
    companion object {
        @JvmStatic
        fun invalidEmptyListConstructorCases() = listOf(
            InvalidConstructorTestCase(
                coordinates = listOf()
            ),
        )

        @JvmStatic
        fun invalidDimensionalityConstructorCases() = listOf(
            InvalidConstructorTestCase(
                coordinates = listOf(
                    Point(0.0, 0.0),
                    Point(0.0, 0.0, 0.0),
                )
            ),
            InvalidConstructorTestCase(
                coordinates = listOf(
                    Point(0.0, 0.0, 0.0),
                )
            ),
        )

        @JvmStatic
        fun invalidNotClosedLoopConstructorCases() = listOf(
            InvalidConstructorTestCase(
                coordinates = listOf(
                    Point(0.0, 0.0),
                    Point(1.0, 1.0),
                    Point(1.0, 2.0),
                )
            ),
            InvalidConstructorTestCase(
                coordinates = listOf(
                    Point(0.0, 0.0),
                    Point(0.0, 1.0),
                    Point(2.0, 1.0),
                )
            ),
        )

        @JvmStatic
        fun sweepCases() = listOf(
            SweepTestCase(
                coordinates = listOf(
                    Point(4.0, 0.0),
                    Point(4.0, 0.0),
                    Point(4.0, 1.0),
                    Point(4.0, 2.0),
                    Point(7.0, 2.0),
                    Point(7.0, 0.0),
                    Point(10.0, 0.0),
                    Point(10.0, 4.0),
                    Point(0.0, 4.0),
                    Point(0.0, 0.0),
                ),
                expected = setOf(
                    Rectangle(topLeftCorner = Point(0.0, 2.0), bottomRightCorner = Point(4.0, 0.0)),
                    Rectangle(topLeftCorner = Point(7.0, 2.0), bottomRightCorner = Point(10.0, 0.0)),
                    Rectangle(topLeftCorner = Point(0.0, 4.0), bottomRightCorner = Point(10.0, 2.0)),
                )
            ),
            SweepTestCase(
                coordinates = listOf(
                    Point(0.0, 0.0),
                    Point(5.0, 0.0),
                    Point(5.0, 2.0),
                    Point(10.0, 2.0),
                    Point(10.0, 5.0),
                    Point(0.0, 5.0),
                ),
                expected = setOf(
                    Rectangle(topLeftCorner = Point(0.0, 2.0), bottomRightCorner = Point(5.0, 0.0)),
                    Rectangle(topLeftCorner = Point(0.0, 5.0), bottomRightCorner = Point(10.0, 2.0)),
                )
            ),
            SweepTestCase(
                coordinates = listOf(
                    Point(3.0, 0.0),
                    Point(7.0, 0.0),
                    Point(7.0, 2.0),
                    Point(10.0, 2.0),
                    Point(10.0, 4.0),
                    Point(7.0, 4.0),
                    Point(7.0, 6.0),
                    Point(3.0, 6.0),
                    Point(3.0, 4.0),
                    Point(0.0, 4.0),
                    Point(0.0, 2.0),
                    Point(3.0, 2.0),
                ),
                expected = setOf(
                    Rectangle(topLeftCorner = Point(3.0, 2.0), bottomRightCorner = Point(7.0, 0.0)),
                    Rectangle(topLeftCorner = Point(0.0, 4.0), bottomRightCorner = Point(10.0, 2.0)),
                    Rectangle(topLeftCorner = Point(3.0, 6.0), bottomRightCorner = Point(7.0, 4.0)),
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
    @MethodSource("invalidDimensionalityConstructorCases")
    fun `throws if trying to initialize with points of dimensionality other than 2`(case: InvalidConstructorTestCase) {
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
