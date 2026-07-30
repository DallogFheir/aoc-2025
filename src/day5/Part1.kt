package day5

object Part1 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveForRangesAndIngredients(
            dayNumber = dayNumber,
            fileName = fileName,
            callback = { ranges, ingredients ->
                ingredients.count { ingredient ->
                    ranges.any { range ->
                        range.contains(ingredient)
                    }
                }.toLong()
            }
        )
    }
}
