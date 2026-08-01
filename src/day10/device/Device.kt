package day10.device

interface Device {
    val size: Int

    fun withAffectedIndices(vararg indices: Int, howManyTimes: Int): Device
}
