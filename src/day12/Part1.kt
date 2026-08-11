package day12

import day12.christmasTreeGrid.ChristmasTreeGrid
import day12.present.Present
import utils.filereader.FileReader


object Part1 {
    fun solve(dayNumber: Int, fileName: String): Long {
        val (presents, christmasTrees) = FileReader(
            dayNumber = dayNumber,
            fileName = fileName
        ).readTwoPartWithParsers(
            part1Parser = {
                mapFromStringWithSeparatorCountAndMapper(content = it, separatorCount = 2) { content ->
                    Present.fromString(content)
                }
            },
            part2Parser = {
                mapFromStringWithSeparatorCountAndMapper(content = it, separatorCount = 1) { content ->
                    ChristmasTreeGrid.fromString(content)
                }
            },
        )

        val presentSizes = presents.map { it.totalArea }

        return christmasTrees.count {
            it.doPresentsFit(presentSizes = presentSizes)
        }.toLong()
    }

    private fun <T> mapFromStringWithSeparatorCountAndMapper(
        content: String,
        separatorCount: Int,
        mapper: (line: String) -> T
    ): List<T> {
        val separator = (0..<separatorCount).joinToString(separator = "") { System.lineSeparator() }

        val definitions = content.split(separator)

        return definitions.map { definition ->
            mapper(definition)
        }
    }
}
