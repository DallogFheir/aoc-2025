package utils.math.euclidean.rectangle

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.math.euclidean.Rectangle
import utils.math.euclidean.point.Point2d

class RectangleTest {
    companion object {
        @JvmStatic
        fun invalidNonIntegerCoordinatesConstructorCases() = listOf(
            InvalidConstructorTestCase(
                topLeftCorner = Point2d(x = 0.5, y = 1.0),
                bottomRightCorner = Point2d(x = 1.0, y = 0.0),
            ),
            InvalidConstructorTestCase(
                topLeftCorner = Point2d(x = 0.0, y = 1.5),
                bottomRightCorner = Point2d(x = 1.0, y = 0.0),
            ),
            InvalidConstructorTestCase(
                topLeftCorner = Point2d(x = 0.0, y = 1.0),
                bottomRightCorner = Point2d(x = 1.5, y = 0.0),
            ),
            InvalidConstructorTestCase(
                topLeftCorner = Point2d(x = 0.0, y = 1.0),
                bottomRightCorner = Point2d(x = 1.0, y = 0.5),
            ),
        )

        @JvmStatic
        fun invalidCornersConstructorCases() = listOf(
            InvalidConstructorTestCase(
                topLeftCorner = Point2d(x = 0.0, y = 1.0),
                bottomRightCorner = Point2d(x = 1.0, y = 2.0),
            ),
            InvalidConstructorTestCase(
                topLeftCorner = Point2d(x = 2.0, y = 1.0),
                bottomRightCorner = Point2d(x = 1.0, y = 0.0),
            ),
        )

        @JvmStatic
        fun fromOppositeCornersCases() = listOf(
            FromOppositeCornersTestCase(
                corner1 = Point2d(x = 0.0, y = 4.0),
                corner2 = Point2d(x = 3.0, y = 0.0),
                expected = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
            ),
            FromOppositeCornersTestCase(
                corner1 = Point2d(x = 3.0, y = 0.0),
                corner2 = Point2d(x = 0.0, y = 4.0),
                expected = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
            ),
            FromOppositeCornersTestCase(
                corner1 = Point2d(x = 0.0, y = 0.0),
                corner2 = Point2d(x = 3.0, y = 4.0),
                expected = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
            ),
            FromOppositeCornersTestCase(
                corner1 = Point2d(x = 3.0, y = 4.0),
                corner2 = Point2d(x = 0.0, y = 0.0),
                expected = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
            ),
            FromOppositeCornersTestCase(
                corner1 = Point2d(x = 3.0, y = 4.0),
                corner2 = Point2d(x = 3.0, y = 0.0),
                expected = Rectangle(
                    topLeftCorner = Point2d(x = 3.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
            ),
        )

        @JvmStatic
        fun areaCases() = listOf(
            AreaTestCase(
                rectangle = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
                expected = 20.0,
            ),
            AreaTestCase(
                rectangle = Rectangle(
                    topLeftCorner = Point2d(x = 3.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
                expected = 5.0,
            ),
            AreaTestCase(
                rectangle = Rectangle(
                    topLeftCorner = Point2d(x = 3.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 4.0)
                ),
                expected = 1.0,
            ),
        )

        @JvmStatic
        fun doesOverlapWithRectangleCases() = listOf(
            DoesOverlapWithRectangleTestCase(
                rectangle = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
                otherRectangle = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 8.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 5.0)
                ),
                expected = false,
            ),
            DoesOverlapWithRectangleTestCase(
                rectangle = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
                otherRectangle = Rectangle(
                    topLeftCorner = Point2d(x = 4.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 8.0, y = 0.0)
                ),
                expected = false,
            ),
            DoesOverlapWithRectangleTestCase(
                rectangle = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
                otherRectangle = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
                expected = true,
            ),
            DoesOverlapWithRectangleTestCase(
                rectangle = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
                otherRectangle = Rectangle(
                    topLeftCorner = Point2d(x = 2.0, y = 5.0),
                    bottomRightCorner = Point2d(x = 4.0, y = 1.0)
                ),
                expected = true,
            ),
            DoesOverlapWithRectangleTestCase(
                rectangle = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
                otherRectangle = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 8.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 4.0)
                ),
                expected = true,
            ),
            DoesOverlapWithRectangleTestCase(
                rectangle = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
                otherRectangle = Rectangle(
                    topLeftCorner = Point2d(x = 3.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 10.0, y = 0.0)
                ),
                expected = true,
            ),
            DoesOverlapWithRectangleTestCase(
                rectangle = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
                otherRectangle = Rectangle(
                    topLeftCorner = Point2d(x = 3.0, y = 8.0),
                    bottomRightCorner = Point2d(x = 8.0, y = 4.0)
                ),
                expected = true,
            ),
        )

        @JvmStatic
        fun subtractCases() = listOf(
            SubtractTestCase(
                rectangle = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
                subtrahend = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 8.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 5.0)
                ),
                expected = setOf(
                    Rectangle(
                        topLeftCorner = Point2d(x = 0.0, y = 4.0),
                        bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                    ),
                ),
            ),
            SubtractTestCase(
                rectangle = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
                subtrahend = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 8.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
                expected = setOf(),
            ),
            SubtractTestCase(
                rectangle = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
                subtrahend = Rectangle(
                    topLeftCorner = Point2d(x = -1.0, y = 5.0),
                    bottomRightCorner = Point2d(x = 4.0, y = 1.0)
                ),
                expected = setOf(
                    Rectangle(
                        topLeftCorner = Point2d(x = 0.0, y = 1.0),
                        bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                    ),
                ),
            ),
            SubtractTestCase(
                rectangle = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
                subtrahend = Rectangle(
                    topLeftCorner = Point2d(x = -1.0, y = 5.0),
                    bottomRightCorner = Point2d(x = 2.0, y = -1.0)
                ),
                expected = setOf(
                    Rectangle(
                        topLeftCorner = Point2d(x = 2.0, y = 4.0),
                        bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                    ),
                ),
            ),
            SubtractTestCase(
                rectangle = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
                subtrahend = Rectangle(
                    topLeftCorner = Point2d(x = 2.0, y = 5.0),
                    bottomRightCorner = Point2d(x = 4.0, y = 1.0)
                ),
                expected = setOf(
                    Rectangle(
                        topLeftCorner = Point2d(x = 0.0, y = 4.0),
                        bottomRightCorner = Point2d(x = 2.0, y = 0.0)
                    ),
                    Rectangle(
                        topLeftCorner = Point2d(x = 2.0, y = 1.0),
                        bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                    ),
                ),
            ),
            SubtractTestCase(
                rectangle = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
                subtrahend = Rectangle(
                    topLeftCorner = Point2d(x = 2.0, y = 3.0),
                    bottomRightCorner = Point2d(x = 4.0, y = 1.0)
                ),
                expected = setOf(
                    Rectangle(
                        topLeftCorner = Point2d(x = 0.0, y = 4.0),
                        bottomRightCorner = Point2d(x = 2.0, y = 0.0)
                    ),
                    Rectangle(
                        topLeftCorner = Point2d(x = 2.0, y = 4.0),
                        bottomRightCorner = Point2d(x = 3.0, y = 3.0)
                    ),
                    Rectangle(
                        topLeftCorner = Point2d(x = 2.0, y = 1.0),
                        bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                    ),
                ),
            ),
            SubtractTestCase(
                rectangle = Rectangle(
                    topLeftCorner = Point2d(x = 0.0, y = 4.0),
                    bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                ),
                subtrahend = Rectangle(
                    topLeftCorner = Point2d(x = 1.0, y = 3.0),
                    bottomRightCorner = Point2d(x = 2.0, y = 1.0)
                ),
                expected = setOf(
                    Rectangle(
                        topLeftCorner = Point2d(x = 0.0, y = 4.0),
                        bottomRightCorner = Point2d(x = 1.0, y = 0.0)
                    ),
                    Rectangle(
                        topLeftCorner = Point2d(x = 1.0, y = 4.0),
                        bottomRightCorner = Point2d(x = 2.0, y = 3.0)
                    ),
                    Rectangle(
                        topLeftCorner = Point2d(x = 1.0, y = 1.0),
                        bottomRightCorner = Point2d(x = 2.0, y = 0.0)
                    ),
                    Rectangle(
                        topLeftCorner = Point2d(x = 2.0, y = 4.0),
                        bottomRightCorner = Point2d(x = 3.0, y = 0.0)
                    ),
                ),
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("invalidNonIntegerCoordinatesConstructorCases")
    fun `throws if trying to initialize with non-integer-coordinate points`(case: InvalidConstructorTestCase) {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Rectangle(
                topLeftCorner = case.topLeftCorner,
                bottomRightCorner = case.bottomRightCorner,
            )
        }
    }

    @ParameterizedTest
    @MethodSource("invalidCornersConstructorCases")
    fun `throws if trying to initialize with invalid corner`(case: InvalidConstructorTestCase) {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Rectangle(
                topLeftCorner = case.topLeftCorner,
                bottomRightCorner = case.bottomRightCorner,
            )
        }
    }

    @ParameterizedTest
    @MethodSource("fromOppositeCornersCases")
    fun `creates a rectangle from opposite corners`(case: FromOppositeCornersTestCase) {
        val result = Rectangle.fromOppositeCorners(case.corner1, case.corner2)

        Assertions.assertEquals(case.expected, result)
    }


    @ParameterizedTest
    @MethodSource("areaCases")
    fun `calculates area correctly`(case: AreaTestCase) {
        val result = case.rectangle.area

        Assertions.assertEquals(case.expected, result)
    }

    @Test
    fun `rectangles with same coordinates are equal`() {
        val topLeftCorner = Point2d(x = 0.0, y = 1.0)
        val bottomRightCorner = Point2d(x = 1.0, y = 0.0)

        val rectangle1 = Rectangle(topLeftCorner = topLeftCorner, bottomRightCorner = bottomRightCorner)
        val rectangle2 = Rectangle(topLeftCorner = topLeftCorner, bottomRightCorner = bottomRightCorner)

        val set = setOf(rectangle1)

        val result = rectangle2 in set

        Assertions.assertTrue(result)
    }

    @Test
    fun `rectangles are not equal to other objects`() {
        val rectangle =
            Rectangle(topLeftCorner = Point2d(x = 0.0, y = 1.0), bottomRightCorner = Point2d(x = 1.0, y = 0.0))
        val notRectangle = listOf<Double>()

        val result = rectangle == notRectangle

        Assertions.assertFalse(result)
    }

    @ParameterizedTest
    @MethodSource("doesOverlapWithRectangleCases")
    fun `returns whether 2 rectangles overlap correctly`(case: DoesOverlapWithRectangleTestCase) {
        val result = case.rectangle.doesOverlapWithRectangle(case.otherRectangle)

        Assertions.assertEquals(case.expected, result)
    }

    @ParameterizedTest
    @MethodSource("subtractCases")
    fun `subtracts rectangles correctly`(case: SubtractTestCase) {
        val result = case.rectangle.subtract(case.subtrahend)

        Assertions.assertEquals(case.expected, result)
    }
}
