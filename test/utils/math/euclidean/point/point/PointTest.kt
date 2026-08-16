package utils.math.euclidean.point.point

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.math.euclidean.point.Point
import kotlin.math.sqrt

class PointTest {
    companion object {
        @JvmStatic
        fun toStringCases() = listOf(
            ToStringTestCase(
                coordinates = listOf(0.0),
                expected = "(0.0)",
            ),
            ToStringTestCase(
                coordinates = listOf(-1.0),
                expected = "(-1.0)",
            ),
            ToStringTestCase(
                coordinates = listOf(1.0),
                expected = "(1.0)",
            ),
            ToStringTestCase(
                coordinates = listOf(1.0, 2.0),
                expected = "(1.0, 2.0)",
            ),
            ToStringTestCase(
                coordinates = listOf(1.0, 2.0, 3.0),
                expected = "(1.0, 2.0, 3.0)",
            )
        )

        @JvmStatic
        fun distanceToCases() = listOf(
            DistanceToTestCase(
                coordinates = listOf(0.0),
                otherCoordinates = listOf(1.0),
                expected = 1.0,
            ),
            DistanceToTestCase(
                coordinates = listOf(1.0),
                otherCoordinates = listOf(-2.0),
                expected = 3.0,
            ),
            DistanceToTestCase(
                coordinates = listOf(-1.0),
                otherCoordinates = listOf(2.0),
                expected = 3.0,
            ),
            DistanceToTestCase(
                coordinates = listOf(0.0, 0.0),
                otherCoordinates = listOf(1.0, 1.0),
                expected = sqrt(2.0),
            ),
            DistanceToTestCase(
                coordinates = listOf(0.0, 0.0),
                otherCoordinates = listOf(-1.0, -1.0),
                expected = sqrt(2.0),
            ),
            DistanceToTestCase(
                coordinates = listOf(0.0, 0.0, 0.0),
                otherCoordinates = listOf(1.0, 1.0, 1.0),
                expected = sqrt(3.0),
            )
        )

        @JvmStatic
        fun invalidDistanceToCases() = listOf(
            InvalidDistanceToTestCase(
                coordinates = listOf(0.0),
                otherCoordinates = listOf(0.0, 0.0),
            ),
            InvalidDistanceToTestCase(
                coordinates = listOf(0.0, 0.0),
                otherCoordinates = listOf(0.0),
            )
        )

        @JvmStatic
        fun isIntegerPointCases() = listOf(
            IsIntegerPointTestCase(
                coordinates = listOf(0.0),
                expected = true,
            ),
            IsIntegerPointTestCase(
                coordinates = listOf(0.5),
                expected = false,
            ),
            IsIntegerPointTestCase(
                coordinates = listOf(0.0, 0.0),
                expected = true,
            ),
            IsIntegerPointTestCase(
                coordinates = listOf(0.5, 0.0),
                expected = false
            ),
            IsIntegerPointTestCase(
                coordinates = listOf(0.0, 0.5),
                expected = false,
            ),
            IsIntegerPointTestCase(
                coordinates = listOf(1.0, 1.0, 1.0),
                expected = true,
            ),
        )
    }

    @Test
    fun `points with same coordinates are equal`() {
        val point1 = Point(0.0)
        val point2 = Point(0.0)

        val set = setOf(point1)

        val result = point2 in set

        Assertions.assertTrue(result)
    }

    @Test
    fun `points are not equal to other objects`() {
        val point = Point(0.0)
        val notPoint = listOf<Double>()

        val result = point == notPoint

        Assertions.assertFalse(result)
    }

    @ParameterizedTest
    @MethodSource("toStringCases")
    fun `converts to string correctly`(case: ToStringTestCase) {
        val cut = Point(*case.coordinates.toDoubleArray())

        val result = cut.toString()

        Assertions.assertEquals(case.expected, result)
    }

    @Test
    fun `throws if trying to initialize with no coordinates`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Point()
        }
    }

    @ParameterizedTest
    @MethodSource("distanceToCases")
    fun `calculates distance to another point correctly`(case: DistanceToTestCase) {
        val cut = Point(*case.coordinates.toDoubleArray())

        val other = Point(*case.otherCoordinates.toDoubleArray())

        val result = cut.distanceTo(other)

        Assertions.assertEquals(case.expected, result)
    }

    @ParameterizedTest
    @MethodSource("invalidDistanceToCases")
    fun `throws if trying to calculate distance between points of different dimensionality`(case: InvalidDistanceToTestCase) {
        val cut = Point(*case.coordinates.toDoubleArray())

        val other = Point(*case.otherCoordinates.toDoubleArray())

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            cut.distanceTo(other)
        }
    }

    @ParameterizedTest
    @MethodSource("isIntegerPointCases")
    fun `returns whether point is an integer point correctly`(case: IsIntegerPointTestCase) {
        val cut = Point(*case.coordinates.toDoubleArray())

        val result = cut.isIntegerPoint()

        Assertions.assertEquals(case.expected, result)
    }
}
