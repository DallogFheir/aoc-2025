package day05

object Part1 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveForRangesAndIngredients(
            dayNumber = dayNumber,
            fileName = fileName,
            callback = { ranges, ingredients ->
                ingredients.count { ingredient ->
                    ranges.any { range ->
                        ingredient in range
                    }
                }.toLong()
            }
        )
    }
}
