package utils.grid.grid

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.grid.Coordinate
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
                    ValueAtCoordinate(coordinate = Coordinate(x = 0, y = 0), value = '1'),
                    ValueAtCoordinate(coordinate = Coordinate(x = 1, y = 0), value = '2'),
                    ValueAtCoordinate(coordinate = Coordinate(x = 0, y = 1), value = '3'),
                    ValueAtCoordinate(coordinate = Coordinate(x = 1, y = 1), value = '4'),
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
                coordinate = Coordinate(x = 0, y = 0),
                expected = true
            ),
            IsInGridTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                coordinate = Coordinate(x = 1, y = 0),
                expected = true
            ),
            IsInGridTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                coordinate = Coordinate(x = 0, y = 1),
                expected = true
            ),
            IsInGridTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                coordinate = Coordinate(x = 1, y = 1),
                expected = true
            ),
            IsInGridTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                coordinate = Coordinate(x = 2, y = 0),
                expected = false
            ),
            IsInGridTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                coordinate = Coordinate(x = 0, y = 2),
                expected = false
            ),
        )

        @JvmStatic
        fun getAtCases() = listOf(
            GetAtTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                coordinate = Coordinate(x = 0, y = 0),
                expected = 1
            ),
            GetAtTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                coordinate = Coordinate(x = 1, y = 0),
                expected = 2
            ),
            GetAtTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                coordinate = Coordinate(x = 0, y = 1),
                expected = 3
            ),
            GetAtTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                coordinate = Coordinate(x = 1, y = 1),
                expected = 4
            ),
        )

        @JvmStatic
        fun setAtCases() = listOf(
            SetAtTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                coordinate = Coordinate(x = 0, y = 0),
                value = 5,
            ),
            SetAtTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                coordinate = Coordinate(x = 1, y = 1),
                value = 5,
            ),
        )

        @JvmStatic
        fun countNeighborsWithValueCases() = listOf(
            CountNeighborsWithValueTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                coordinate = Coordinate(x = 0, y = 0),
                value = 2,
                expected = 1
            ),
            CountNeighborsWithValueTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                coordinate = Coordinate(x = 0, y = 0),
                value = 3,
                expected = 1
            ),
            CountNeighborsWithValueTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                coordinate = Coordinate(x = 0, y = 0),
                value = 4,
                expected = 1
            ),
            CountNeighborsWithValueTestCase(
                grid = arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9)),
                coordinate = Coordinate(x = 0, y = 0),
                value = 6,
                expected = 0
            ),
            CountNeighborsWithValueTestCase(
                grid = arrayOf(arrayOf(1, 1, 1), arrayOf(1, 1, 1), arrayOf(1, 1, 1)),
                coordinate = Coordinate(x = 0, y = 0),
                value = 1,
                expected = 3
            ),
            CountNeighborsWithValueTestCase(
                grid = arrayOf(arrayOf(1, 1, 1), arrayOf(1, 1, 1), arrayOf(1, 1, 1)),
                coordinate = Coordinate(x = 1, y = 1),
                value = 1,
                expected = 8
            ),
            CountNeighborsWithValueTestCase(
                grid = arrayOf(arrayOf(1, 1, 1), arrayOf(1, 1, 1), arrayOf(1, 1, 1)),
                coordinate = Coordinate(x = 2, y = 2),
                value = 1,
                expected = 3
            ),
        )

        @JvmStatic
        fun invalidCoordinateCases() = listOf(
            InvalidCoordinateTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                coordinate = Coordinate(x = 3, y = 0),
            ),
            InvalidCoordinateTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                coordinate = Coordinate(x = 0, y = 3),
            ),
        )

        @JvmStatic
        fun flatMapWithCoordinateCases() = listOf(
            FlatMapWithCoordinateTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                callback = { _, item -> item },
                expected = listOf(1, 2, 3, 4)
            ),
            FlatMapWithCoordinateTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                callback = { coordinate, item -> coordinate.x + coordinate.y + item },
                expected = listOf(1, 3, 4, 6)
            ),
        )

        @JvmStatic
        fun findCoordinateForCases() = listOf(
            FindCoordinateForTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                valueToSearchFor = 1,
                expected = Coordinate(x = 0, y = 0),
            ),
            FindCoordinateForTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                valueToSearchFor = 2,
                expected = Coordinate(x = 1, y = 0),
            ),
            FindCoordinateForTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                valueToSearchFor = 3,
                expected = Coordinate(x = 0, y = 1),
            ),
            FindCoordinateForTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                valueToSearchFor = 4,
                expected = Coordinate(x = 1, y = 1),
            ),
        )

        @JvmStatic
        fun invalidFindCoordinateForCases() = listOf(
            InvalidFindCoordinateForTestCase(
                grid = arrayOf(arrayOf(1, 2), arrayOf(3, 4)),
                valueToSearchFor = 5,
            )
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
            val value = cut.getAt(coordinate = it.coordinate)

            Assertions.assertEquals(
                it.value,
                value,
                "fromString for string ${case.string} should create a grid with value ${it.value} at coordinate ${it.coordinate}, got $value"
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

        val result = cut.isInGrid(coordinate = case.coordinate)

        Assertions.assertEquals(
            case.expected,
            result,
            "isInGrid for grid $cut at coordinate ${case.coordinate} should return ${case.expected}, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("getAtCases")
    fun `gets value at coordinate correctly`(case: GetAtTestCase<Int>) {
        val cut = Grid(grid = case.grid)

        val result = cut.getAt(coordinate = case.coordinate)

        Assertions.assertEquals(
            case.expected,
            result,
            "getAt for grid $cut at coordinate ${case.coordinate} should return ${case.expected}, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("invalidCoordinateCases")
    fun `throws if trying to get at invalid coordinate`(case: InvalidCoordinateTestCase<Int>) {
        val cut = Grid(grid = case.grid)

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            cut.getAt(coordinate = case.coordinate)
        }
    }

    @ParameterizedTest
    @MethodSource("setAtCases")
    fun `sets value at coordinate correctly`(case: SetAtTestCase<Int>) {
        val cut = Grid(grid = case.grid)

        cut.setAt(coordinate = case.coordinate, value = case.value)

        val result = cut.getAt(coordinate = case.coordinate)

        Assertions.assertEquals(
            case.value,
            result,
            "setAt for grid $cut, coordinate ${case.coordinate} should set grid cell to ${case.value}, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("invalidCoordinateCases")
    fun `throws if trying to set at invalid coordinate`(case: InvalidCoordinateTestCase<Int>) {
        val cut = Grid(grid = case.grid)

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            cut.setAt(coordinate = case.coordinate, value = 1)
        }
    }

    @ParameterizedTest
    @MethodSource("countNeighborsWithValueCases")
    fun `counts neighbors of given coordinate with given value correctly`(case: CountNeighborsWithValueTestCase<Int>) {
        val cut = Grid(grid = case.grid)

        val result = cut.countNeighborsWithValue(coordinate = case.coordinate, value = case.value)

        Assertions.assertEquals(
            case.expected,
            result,
            "countNeighborsWithValue for grid $cut, coordinate ${case.coordinate} and value ${case.value} should return ${case.expected}, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("invalidCoordinateCases")
    fun `throws if trying to count neighbors of invalid coordinate`(case: InvalidCoordinateTestCase<Int>) {
        val cut = Grid(grid = case.grid)

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            cut.countNeighborsWithValue(coordinate = case.coordinate, value = 1)
        }
    }

    @ParameterizedTest
    @MethodSource("flatMapWithCoordinateCases")
    fun `flat-maps grid with coordinate correctly`(case: FlatMapWithCoordinateTestCase<Int, Int>) {
        val cut = Grid(grid = case.grid)

        val result = cut.flatMapWithCoordinate(case.callback)

        Assertions.assertEquals(
            case.expected,
            result,
            "flatMap for grid $cut should return ${case.expected}, got $result"
        )
    }

    @ParameterizedTest
    @MethodSource("findCoordinateForCases")
    fun `finds coordinate for given value correctly`(case: FindCoordinateForTestCase<Int>) {
        val cut = Grid(grid = case.grid)

        val result = cut.findCoordinateFor(case.valueToSearchFor)

        Assertions.assertEquals(
            case.expected,
            result
        )
    }

    @ParameterizedTest
    @MethodSource("invalidFindCoordinateForCases")
    fun `throws if given value is not found in grid`(case: InvalidFindCoordinateForTestCase<Int>) {
        val cut = Grid(grid = case.grid)

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            cut.findCoordinateFor(case.valueToSearchFor)
        }
    }
}
