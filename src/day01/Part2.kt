package day01

import day01.rotation.Rotation
import day01.safedial.SafeDial
import utils.filereader.FileReader

object Part2 {
    fun solve(dayNumber: Int, fileName: String): Int {
        val rotations = FileReader(dayNumber = dayNumber, fileName = fileName).readLinesWithParser {
            Rotation.fromString(it)
        }

        val safeDial = SafeDial(size = DIAL_SIZE, startingPoint = DIAL_STARTING_POINT)

        rotations.forEach { safeDial.rotate(it) }

        return safeDial.pointedAtZeroCount
    }
}
