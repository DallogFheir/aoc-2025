package day10

import day10.bfsSearcher.IndicatorLightsBfsSearcher
import day10.button.parseButtons
import day10.device.IndicatorLights
import day10.machineSpecification.IndicatorLightsMachineSpecification
import day10.machineSpecification.splitMachineSpecification
import utils.filereader.FileReader

private const val INDICATOR_LIGHT_PREFIX = "["
private const val INDICATOR_LIGHT_SUFFIX = "]"
private const val INDICATOR_LIGHT_SWITCHED_ON = "#"
private const val INDICATOR_LIGHT_SWITCHED_OFF = "."

object Part1 {
    fun solve(dayNumber: Int, fileName: String): Long {
        val machineSpecifications = FileReader(dayNumber = dayNumber, fileName = fileName).readLinesWithParser { line ->
            val lineParts = splitMachineSpecification(line)

            val lightString =
                lineParts.first().removePrefix(INDICATOR_LIGHT_PREFIX).removeSuffix(INDICATOR_LIGHT_SUFFIX)

            val lights = lightString.map {
                when (it.toString()) {
                    INDICATOR_LIGHT_SWITCHED_ON -> true
                    INDICATOR_LIGHT_SWITCHED_OFF -> false
                    else -> throw IllegalArgumentException("Unknown indicator light symbol: $it")
                }
            }.toTypedArray()

            val indicatorLights = IndicatorLights(lights = lights)

            val buttons = parseButtons(lineParts)

            IndicatorLightsMachineSpecification(
                indicatorLights = indicatorLights,
                buttons = buttons,
            )
        }

        return machineSpecifications.sumOf {
            val searcher = IndicatorLightsBfsSearcher(
                machineSpecification = it,
            )

            searcher.search().toLong()
        }
    }
}
