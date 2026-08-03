package day08

object Part2 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveForDistancesAndCircuits(dayNumber = dayNumber, fileName = fileName) { distances, circuits ->
            val pointPairs = distances.toMutableList()

            var pointPair: PointPairWithDistance? = null
            while (circuits.getDisjointSets().size > 1) {
                pointPair = pointPairs.removeFirst()

                circuits.union(pointPair.point1, pointPair.point2)
            }

            require(pointPair != null)

            (pointPair.point1.x * pointPair.point2.x).toLong()
        }
    }
}
