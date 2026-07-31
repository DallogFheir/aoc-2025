package utils.math.euclidean.point

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.math.euclidean.Point
import kotlin.math.sqrt

class PointTest {
    companion object {
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
}
