package day1

import day1.rotation.Rotation
import day1.safedial.SafeDial
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
