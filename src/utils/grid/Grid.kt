package utils.grid

import utils.product

class Grid<T>(private val grid: Array<Array<T>>) {
    private val width: Int
    private val height: Int

    init {
        require(grid.isNotEmpty() && grid.any { it.isNotEmpty() }) {
            "Grid cannot be empty"
        }

        require(grid.map { row -> row.size }.toSet().size == 1) {
            "All rows in grid must have same size"
        }

        height = grid.size
        width = grid.first().size
    }

    override fun toString(): String {
        val gridString = grid.joinToString(", ") { row ->
            val rowString = row.joinToString(", ")

            "[$rowString]"
        }

        return "[$gridString]"
    }

    fun isInGrid(coordinate: Coordinate): Boolean {
        return coordinate.x < width && coordinate.y < height
    }

    fun getAt(coordinate: Coordinate): T {
        ensureCoordinateInGrid(coordinate)

        return grid[coordinate.y][coordinate.x]
    }

    fun setAt(coordinate: Coordinate, value: T) {
        ensureCoordinateInGrid(coordinate)

        grid[coordinate.y][coordinate.x] = value
    }

    private fun ensureCoordinateInGrid(coordinate: Coordinate) {
        if (!isInGrid(coordinate)) {
            throw IllegalArgumentException("Grid coordinate $coordinate out of range for grid with size $width×$height")
        }
    }

    fun countNeighborsWithValue(coordinate: Coordinate, value: T): Int {
        ensureCoordinateInGrid(coordinate)

        val neighbors = getNeighbors(coordinate)

        return neighbors.count { it == value }
    }

    private fun getNeighbors(coordinate: Coordinate): List<T> {
        val coordinateAddends = listOf(-1, 0, 1)

        val addendsForXAndY = product(coordinateAddends, coordinateAddends)

        return addendsForXAndY.mapNotNull {
            val (xAddend, yAddend) = it

            if (xAddend == 0 && yAddend == 0) {
                return@mapNotNull null
            }

            val neighborX = coordinate.x + xAddend
            val neighborY = coordinate.y + yAddend

            if (!Coordinate.isValid(x = neighborX, y = neighborY)) {
                return@mapNotNull null
            }

            val neighborCoordinate = Coordinate(x = neighborX, y = neighborY)

            if (!isInGrid(neighborCoordinate)) {
                return@mapNotNull null
            }

            getAt(neighborCoordinate)
        }
    }

    fun <U> flatMapWithCoordinate(callback: (Coordinate, T) -> U): List<U> {
        return grid.flatMapIndexed { y, row -> row.mapIndexed { x, item -> callback(Coordinate(x = x, y = y), item) } }
    }

    companion object {
        fun fromString(string: String): Grid<Char> {
            val lines = string.split(System.lineSeparator())

            val cells = lines.map {
                it.toList().toTypedArray()
            }.toTypedArray()

            return Grid(grid = cells)
        }
    }
}
