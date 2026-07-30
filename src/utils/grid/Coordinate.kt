package utils.grid

data class Coordinate(
    val x: Int,
    val y: Int,
) {
    init {
        require(x >= 0) {
            "x must be non-negative"
        }

        require(y >= 0) {
            "y must be non-negative"
        }
    }

    companion object {
        fun isValid(x: Int, y: Int): Boolean {
            return x >= 0 && y >= 0
        }
    }
}
