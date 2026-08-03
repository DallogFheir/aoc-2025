package day05

import utils.range.Range

object Part2 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveForRangesAndIngredients(
            dayNumber = dayNumber,
            fileName = fileName,
            callback = ::solveForRangesAndIngredients,
        )
    }

    private fun solveForRangesAndIngredients(ranges: List<Range>, ingredients: List<Long>): Long {
        val mergedRanges = mutableListOf<Range>()
        val remainingRanges = ranges.toMutableList()

        while (remainingRanges.isNotEmpty()) {
            val firstRange = remainingRanges.removeFirst()

            var wasThereMerge = false

            for (remainingRange in remainingRanges) {
                if (firstRange.doesOverlapWithRange(remainingRange)) {
                    val newRange = firstRange.mergeWithRange(remainingRange)

                    remainingRanges.remove(remainingRange)

                    remainingRanges.add(newRange)

                    wasThereMerge = true

                    break
                }
            }

            if (!wasThereMerge) {
                mergedRanges.add(firstRange)
            }
        }

        return mergedRanges.sumOf { it.countInRange() }.toLong()
    }
}
