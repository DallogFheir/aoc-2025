package day03

private const val ACTIVATABLE_BATTERY_COUNT = 12

object Part2 {
    fun solve(dayNumber: Int, fileName: String): Long {
        return solveWithActivatableBatteryCount(
            dayNumber = dayNumber,
            fileName = fileName,
            activatableBatteryCount = ACTIVATABLE_BATTERY_COUNT
        )
    }
}
