package day10.machineSpecification

import day10.button.Button
import day10.device.JoltageCounter

data class JoltageCounterMachineSpecification(
    val joltageCounter: JoltageCounter,
    val buttons: List<Button>,
)
