package day12

import day12.christmasTree.ChristmasTree
import day12.christmasTree.Present
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
                    ChristmasTree.fromString(content)
                }
            },
        )
        return christmasTrees.count {
            it.doPresentsFit(presents = presents)
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
