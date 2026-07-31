package day10.machineSpecification

interface Device {
    val size: Int

    fun withAffectedIndices(vararg indices: Int): Device
}
