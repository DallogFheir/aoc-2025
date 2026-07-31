package day10.machineSpecification.device

interface Device {
    val size: Int

    fun withAffectedIndices(vararg indices: Int): Device
}
