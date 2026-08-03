package day03

private const val ACTIVATABLE_BATTERY_COUNT = 2

object Part1 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveWithActivatableBatteryCount(
            dayNumber = dayNumber,
            fileName = fileName,
            activatableBatteryCount = ACTIVATABLE_BATTERY_COUNT
        )
    }
}
