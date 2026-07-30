package utils.grid.grid

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.grid.Grid

class GridTest {
    companion object {
        @JvmStatic
        fun invalidEmptyConstructorCases() = listOf(
            InvalidConstructorTestCase<Int>(grid = arrayOf()),
            InvalidConstructorTestCase(grid = arrayOf(arrayOf())),
        )

        @JvmStatic
        fun invalidUnequalConstructorCases() = listOf(
            InvalidConstructorTestCase(grid = arrayOf(arrayOf(1), arrayOf(1, 2))),
        )

        @JvmStatic
        fun fromStringCases() = listOf(
            FromStringTestCase(
                string = listOf("12", "34").joinToString(separator = System.lineSeparator()),
                expectedValuesAtCoordinates = listOf(
                    ValueAtCoordinate(x = 0, y = 0, value = '1'),
                    ValueAtCoordinate(x = 1, y = 0, value = '2'),
                    ValueAtCoordinate(x = 0, y = 1, value = '3'),
                    ValueAtCoordinate(x = 1, y = 1, value = '4'),
                )
            ),
        )

        @JvmStatic
        fun toStringCases() = listOf(
            ToStringTestCase(
                grid = arrayOf(arrayOf(1), arrayOf(2)),
                expected = "[[1], [2]]",
            ),
            ToStringTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                expected = "[[1, 2], [3, 4]]",
            ),
            ToStringTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4), arrayOf(5, 6)),
                expected = "[[1, 2], [3, 4], [5, 6]]",
            ),
        )

        @JvmStatic
        fun isInGridCases() = listOf(
            IsInGridTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = 0,
                y = 0,
                expected = true
            ),
            IsInGridTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = 1,
                y = 0,
                expected = true
            ),
            IsInGridTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = 0,
                y = 1,
                expected = true
            ),
            IsInGridTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = 1,
                y = 1,
                expected = true
            ),
            IsInGridTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = 2,
                y = 0,
                expected = false
            ),
            IsInGridTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = 0,
                y = 2,
                expected = false
            ),
        )

        @JvmStatic
        fun getAtCases() = listOf(
            GetAtTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = 0,
                y = 0,
                expected = 1
            ),
            GetAtTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = 1,
                y = 0,
                expected = 2
            ),
            GetAtTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = 0,
                y = 1,
                expected = 3
            ),
            GetAtTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = 1,
                y = 1,
                expected = 4
            ),
        )

        @JvmStatic
        fun setAtCases() = listOf(
            SetAtTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = 0,
                y = 0,
                value = 5,
            ),
            SetAtTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = 1,
                y = 1,
                value = 5,
            ),
        )

        @JvmStatic
        fun countNeighborsWithValueCases() = listOf(
            CountNeighborsWithValueTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = 0,
                y = 0,
                value = 2,
                expected = 1
            ),
            CountNeighborsWithValueTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = 0,
                y = 0,
                value = 3,
                expected = 1
            ),
            CountNeighborsWithValueTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = 0,
                y = 0,
                value = 4,
                expected = 1
            ),
            CountNeighborsWithValueTestCase(
                grid = arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9)),
                x = 0,
                y = 0,
                value = 6,
                expected = 0
            ),
            CountNeighborsWithValueTestCase(
                grid = arrayOf(arrayOf(1, 1, 1), arrayOf(1, 1, 1), arrayOf(1, 1, 1)),
                x = 0,
                y = 0,
                value = 1,
                expected = 3
            ),
            CountNeighborsWithValueTestCase(
                grid = arrayOf(arrayOf(1, 1, 1), arrayOf(1, 1, 1), arrayOf(1, 1, 1)),
                x = 1,
                y = 1,
                value = 1,
                expected = 8
            ),
            CountNeighborsWithValueTestCase(
                grid = arrayOf(arrayOf(1, 1, 1), arrayOf(1, 1, 1), arrayOf(1, 1, 1)),
                x = 2,
                y = 2,
                value = 1,
                expected = 3
            ),
        )

        @JvmStatic
        fun invalidCoordinateCases() = listOf(
            InvalidCoordinateTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = -1,
                y = 0,
            ),
            InvalidCoordinateTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = 0,
                y = -1,
            ),
            InvalidCoordinateTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = 3,
                y = 0,
            ),
            InvalidCoordinateTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                x = 0,
                y = 3,
            ),
        )

        @JvmStatic
        fun flatMapWithCoordinateTestCases() = listOf(
            FlatMapWithCoordinateTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                callback = { _, _, item -> item },
                expected = listOf(1, 2, 3, 4)
            ),
            FlatMapWithCoordinateTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                callback = { x, y, item -> x + y + item },
                expected = listOf(1, 3, 4, 6)
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("invalidEmptyConstructorCases")
    fun `throws if trying to initialize with empty array`(case: InvalidConstructorTestCase<Int>) {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Grid(grid = case.grid)
        }
    }

    @ParameterizedTest
    @MethodSource("invalidUnequalConstructorCases")
    fun `throws if trying to initialize with arrays of unequal sizes`(case: InvalidConstructorTestCase<Int>) {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Grid(grid = case.grid)
        }
    }

    @ParameterizedTest
    @MethodSource("fromStringCases")
    fun `creates grid from string correctly`(case: FromStringTestCase<Char>) {
        val cut = Grid.fromString(case.string)

        case.expectedValuesAtCoordinates.forEach {
            val value = cut.getAt(x = it.x, y = it.y)

            Assertions.assertEquals(
                it.value,
                value,
                "fromString for string ${case.string} should create a grid with value ${it.value} at coordinate (x=${it.x}, y=${it.y}), got $value"
            )
        }
    }

    @ParameterizedTest
    @MethodSource("toStringCases")
    fun `converts to string correctly`(case: ToStringTestCase<Int>) {
        val cut = Grid(grid = case.grid)

        val result = cut.toString()

        Assertions.assertEquals(
            case.expected,
            result,
            "toString should return ${case.expected}, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("isInGridCases")
    fun `checks if coordinate is in grid correctly`(case: IsInGridTestCase<Int>) {
        val cut = Grid(grid = case.grid)

        val result = cut.isInGrid(x = case.x, y = case.y)

        Assertions.assertEquals(
            case.expected,
            result,
            "isInGrid for grid $cut at coordinate (x=${case.x}, y=${case.y}) should return ${case.expected}, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("getAtCases")
    fun `gets value at coordinate correctly`(case: GetAtTestCase<Int>) {
        val cut = Grid(grid = case.grid)

        val result = cut.getAt(x = case.x, y = case.y)

        Assertions.assertEquals(
            case.expected,
            result,
            "getAt for grid $cut at coordinate (x=${case.x}, y=${case.y}) should return ${case.expected}, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("invalidCoordinateCases")
    fun `throws if trying to get at invalid coordinate`(case: InvalidCoordinateTestCase<Int>) {
        val cut = Grid(grid = case.grid)

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            cut.getAt(x = case.x, y = case.y)
        }
    }

    @ParameterizedTest
    @MethodSource("setAtCases")
    fun `sets value at coordinate correctly`(case: SetAtTestCase<Int>) {
        val cut = Grid(grid = case.grid)

        cut.setAt(x = case.x, y = case.y, value = case.value)

        val result = cut.getAt(x = case.x, y = case.y)

        Assertions.assertEquals(
            case.value,
            result,
            "setAt for grid $cut, coordinate (x=${case.x}, y=${case.y}) should set grid cell to ${case.value}, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("invalidCoordinateCases")
    fun `throws if trying to set at invalid coordinate`(case: InvalidCoordinateTestCase<Int>) {
        val cut = Grid(grid = case.grid)

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            cut.setAt(x = case.x, y = case.y, value = 1)
        }
    }

    @ParameterizedTest
    @MethodSource("countNeighborsWithValueCases")
    fun `counts neighbors of given coordinate with given value correctly`(case: CountNeighborsWithValueTestCase<Int>) {
        val cut = Grid(grid = case.grid)

        val result = cut.countNeighborsWithValue(x = case.x, y = case.y, value = case.value)

        Assertions.assertEquals(
            case.expected,
            result,
            "countNeighborsWithValue for grid $cut, coordinate (x=${case.x}, y=${case.y}) and value ${case.value} should return ${case.expected}, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("invalidCoordinateCases")
    fun `throws if trying to count neighbors of invalid coordinate`(case: InvalidCoordinateTestCase<Int>) {
        val cut = Grid(grid = case.grid)

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            cut.countNeighborsWithValue(x = case.x, y = case.y, value = 1)
        }
    }

    @ParameterizedTest
    @MethodSource("flatMapWithCoordinateTestCases")
    fun `flat-maps grid with coordinate correctly`(case: FlatMapWithCoordinateTestCase<Int, Int>) {
        val cut = Grid(grid = case.grid)

        val result = cut.flatMapWithCoordinate(case.callback)

        Assertions.assertEquals(
            case.expected,
            result,
            "flatMap for grid $cut should return ${case.expected}, got $result"
        )
    }
}
