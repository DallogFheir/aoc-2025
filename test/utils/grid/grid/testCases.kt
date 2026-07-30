package utils.grid.grid

import utils.grid.Coordinate

data class ValueAtCoordinate<T>(
    val coordinate: Coordinate,
    val value: T,
)

data class InvalidCoordinateTestCase<T>(
    val grid: Array<Array<T>>,
    val coordinate: Coordinate,
)

data class InvalidConstructorTestCase<T>(
    val grid: Array<Array<T>>,
)

data class FromStringTestCase<T>(
    val string: String,
    val expectedValuesAtCoordinates: List<ValueAtCoordinate<T>>,
)

data class ToStringTestCase<T>(
    val grid: Array<Array<T>>,
    val expected: String,
)

data class IsInGridTestCase<T>(
    val grid: Array<Array<T>>,
    val coordinate: Coordinate,
    val expected: Boolean,
)

data class GetAtTestCase<T>(
    val grid: Array<Array<T>>,
    val coordinate: Coordinate,
    val expected: T,
)

data class SetAtTestCase<T>(
    val grid: Array<Array<T>>,
    val coordinate: Coordinate,
    val value: T,
)

data class CountNeighborsWithValueTestCase<T>(
    val grid: Array<Array<T>>,
    val coordinate: Coordinate,
    val value: T,
    val expected: Int,
)

data class FlatMapWithCoordinateTestCase<T, U>(
    val grid: Array<Array<T>>,
    val callback: (Coordinate, T) -> U,
    val expected: List<T>,
)
