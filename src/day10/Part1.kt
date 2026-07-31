package day10

import day10.bfsSearcher.BfsSearcher
import day10.machineSpecification.Button
import day10.machineSpecification.IndicatorLights
import day10.machineSpecification.MachineSpecification
import utils.filereader.FileReader

private const val MACHINE_SPECIFICATION_SEPARATOR = " "
private const val INDICATOR_LIGHT_PREFIX = "["
private const val INDICATOR_LIGHT_SUFFIX = "]"
private const val INDICATOR_LIGHT_SWITCHED_ON = "#"
private const val INDICATOR_LIGHT_SWITCHED_OFF = "."
private const val BUTTON_PREFIX = "("
private const val BUTTON_SUFFIX = ")"
private const val BUTTON_LIGHT_SEPARATOR = ","

object Part1 {
    fun solve(dayNumber: Int, fileName: String): Long {
        val machineSpecifications = FileReader(dayNumber = dayNumber, fileName = fileName).readLinesWithParser { line ->
            val parts = line.split(MACHINE_SPECIFICATION_SEPARATOR)

            val lightString = parts.first().removePrefix(INDICATOR_LIGHT_PREFIX).removeSuffix(INDICATOR_LIGHT_SUFFIX)
            val lights = lightString.map {
                when (it.toString()) {
                    INDICATOR_LIGHT_SWITCHED_ON -> true
                    INDICATOR_LIGHT_SWITCHED_OFF -> false
                    else -> throw IllegalArgumentException("Unknown indicator light symbol: $it")
                }
            }.toTypedArray()

            val buttonStrings = parts.slice(1..<parts.lastIndex)
            val buttons = buttonStrings.map { buttonString ->
                val buttonLightStrings =
                    buttonString.removePrefix(BUTTON_PREFIX).removeSuffix(BUTTON_SUFFIX).split(BUTTON_LIGHT_SEPARATOR)

                val buttonLights = buttonLightStrings.map { it.toInt() }

                Button(toggledLightIndices = buttonLights)
            }

            MachineSpecification(
                indicatorLights = IndicatorLights(lights = lights),
                buttons = buttons,
            )
        }

        return machineSpecifications.sumOf {
            val searcher = BfsSearcher(machineSpecification = it)

            searcher.search()
        }.toLong()
    }
}
