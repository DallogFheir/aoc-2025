package utils.math.euclidean.point.point3d

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.math.euclidean.point.Point3d

class Point3dTest {
    companion object {
        @JvmStatic
        fun coordinateCases() = listOf(
            CoordinateTestCase(
                x = 0.0,
                y = 0.0,
                z = 0.0,
            ),
            CoordinateTestCase(
                x = 1.0,
                y = 2.0,
                z = 3.0,
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("coordinateCases")
    fun `coordinates are available at fields x, y, z`(case: CoordinateTestCase) {
        val cut = Point3d(x = case.x, y = case.y, z = case.z)

        Assertions.assertEquals(case.x, cut.x)
        Assertions.assertEquals(case.y, cut.y)
        Assertions.assertEquals(case.z, cut.z)
    }
}
