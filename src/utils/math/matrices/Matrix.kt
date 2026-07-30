package utils.math.matrices

class Matrix<T>(val matrix: List<List<T>>) {
    init {
        require(matrix.map { it.size }.toSet().size == 1) {
            "All rows should have same size"
        }

        require(matrix.first().isNotEmpty()) {
            "Matrix cannot be empty"
        }
    }

    private val width = matrix.first().size
    private val height = matrix.size

    override fun toString(): String {
        val elementSeparator = ", "

        return "[${
            matrix.joinToString(separator = elementSeparator) { row ->
                "[${row.joinToString(separator = elementSeparator)}]"
            }
        }]"
    }

    fun transpose(): Matrix<T> {
        val matrix = List(width) { column ->
            List(height) { row ->
                matrix[row][column]
            }
        }

        return Matrix(matrix)
    }
}
