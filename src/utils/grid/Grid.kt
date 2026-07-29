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

    fun isInGrid(x: Int, y: Int): Boolean {
        return x in 0 until width && y in 0 until height
    }

    fun getAt(x: Int, y: Int): T {
        ensureCoordinateInGrid(x = x, y = y)

        return grid[y][x]
    }

    fun setAt(x: Int, y: Int, value: T) {
        ensureCoordinateInGrid(x = x, y = y)

        grid[y][x] = value
    }

    private fun ensureCoordinateInGrid(x: Int, y: Int) {
        if (!isInGrid(x, y)) {
            throw IllegalArgumentException("Grid coordinate (x=${x}, y=${y}) out of range for grid with size $width×$height")
        }
    }

    fun countNeighborsWithValue(x: Int, y: Int, value: T): Int {
        ensureCoordinateInGrid(x = x, y = y)

        val neighbors = getNeighbors(x = x, y = y)

        return neighbors.count { it == value }
    }

    private fun getNeighbors(x: Int, y: Int): List<T> {
        val coordinateAddends = listOf(-1, 0, 1)

        val addendsForXAndY = product(coordinateAddends, coordinateAddends)

        return addendsForXAndY.mapNotNull {
            val (xAddend, yAddend) = it

            if (xAddend == 0 && yAddend == 0) {
                return@mapNotNull null
            }

            val neighborX = x + xAddend
            val neighborY = y + yAddend

            if (isInGrid(x = neighborX, y = neighborY)) {
                return@mapNotNull getAt(x = neighborX, y = neighborY)
            }

            null
        }
    }

    fun <U> flatMapWithCoordinate(callback: (Int, Int, T) -> U): List<U> {
        return grid.flatMapIndexed { y, row -> row.mapIndexed { x, item -> callback(x, y, item) } }
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
