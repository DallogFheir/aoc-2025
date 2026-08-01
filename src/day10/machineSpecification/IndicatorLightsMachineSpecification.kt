package day10.machineSpecification

import day10.button.Button
import day10.device.IndicatorLights

data class IndicatorLightsMachineSpecification(
    val indicatorLights: IndicatorLights,
    val buttons: List<Button>,
)
