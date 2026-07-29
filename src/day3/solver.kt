package day3

import day3.battery.BatteryBank
import utils.filereader.FileReader


fun solveWithActivatableBatteryCount(
    dayNumber: Int,
    fileName: String,
    activatableBatteryCount: Int,
): Long {
    val batteryBanks = FileReader(dayNumber = dayNumber, fileName = fileName).readLinesWithParser { line ->
        BatteryBank.fromString(string = line, activatableBatteryCount = activatableBatteryCount)
    }

    val largestPossibleJoltages = batteryBanks.map { it.findLargestPossibleJoltage() }

    return largestPossibleJoltages.sum()
}
