package day8


object Part1 {
    fun solve(dayNumber: Int, fileName: String, pairCount: Int): Long {
        return solveForDistancesAndCircuits(dayNumber = dayNumber, fileName = fileName) { distances, circuits ->
            (0..<pairCount).forEach { index ->
                val pairPointWithDistance = distances[index]

                circuits.union(pairPointWithDistance.point1, pairPointWithDistance.point2)
            }

            val finalCircuits = circuits.getDisjointSets()

            val sortedCircuitSizes =
                finalCircuits.sortedByDescending { it.size }.map { it.size }.slice(0..<3).toMutableList()

            while (sortedCircuitSizes.size < 3) {
                sortedCircuitSizes.add(1)
            }

            sortedCircuitSizes.fold(1L) { total, size -> total * size.toLong() }
        }
    }
}
