package day10.machineSpecification

import day10.machineSpecification.device.Device

data class MachineSpecification(
    val device: Device,
    val buttons: List<Button>,
)
