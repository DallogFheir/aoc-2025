package day01

import day01.rotation.Rotation
import day01.safedial.SafeDial
import utils.filereader.FileReader

object Part1 {
    fun solve(dayNumber: Int, fileName: String): Int {
        val rotations = FileReader(dayNumber = dayNumber, fileName = fileName).readLinesWithParser {
            Rotation.fromString(it)
        }

        val safeDial = SafeDial(size = DIAL_SIZE, startingPoint = DIAL_STARTING_POINT)

        val rotationsEndingAtZero = rotations.filter {
            safeDial.rotate(it)

            safeDial.currentPoint == 0
        }

        return rotationsEndingAtZero.size
    }
}
