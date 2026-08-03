package day09

import utils.math.euclidean.SweeplineDecomposition


object Part2 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveForPointsAndPossibleRectangles(
            dayNumber = dayNumber,
            fileName = fileName
        ) { points, possibleRectangles ->
            val sweeplineDecomposition = SweeplineDecomposition(coordinates = points)
            val slabs = sweeplineDecomposition.sweep()

            for (possibleRectangle in possibleRectangles.sortedByDescending { it.area }) {
                val intersectingSlabs = slabs.filter { possibleRectangle.doesOverlapWithRectangle(it) }

                var remainingSubrectangles = listOf(possibleRectangle)

                for (slab in intersectingSlabs) {
                    remainingSubrectangles = remainingSubrectangles.flatMap { it.subtract(slab) }

                    if (remainingSubrectangles.isEmpty()) {
                        return@solveForPointsAndPossibleRectangles possibleRectangle.area.toLong()
                    }
                }
            }

            throw IllegalStateException("Solution not found")
        }
    }
}
