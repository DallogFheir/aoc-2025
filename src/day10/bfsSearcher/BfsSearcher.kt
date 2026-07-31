package day10.bfsSearcher

import day10.machineSpecification.Button
import day10.machineSpecification.device.Device
import day10.machineSpecification.MachineSpecification

private data class BfsQueueItem(
    val parent: Button?,
    val device: Device,
    val pathButtons: List<Button>,
)

class BfsSearcher(
    private val machineSpecification: MachineSpecification,
    private val emptyDeviceFactory: () -> Device
) {
    private val grandparentPathToUsedButtons = mutableMapOf<List<Button>, MutableList<Button>>()

    fun search(): Int {
        grandparentPathToUsedButtons.clear()

        val queue = mutableListOf(
            BfsQueueItem(
                parent = null,
                device = emptyDeviceFactory(),
                pathButtons = listOf(),
            )
        )

        while (queue.isNotEmpty()) {
            val queueItem = queue.removeFirst()

            val previousLevel = queueItem.pathButtons.size

            if (queueItem.device == machineSpecification.device) {
                return previousLevel
            }

            val grandparentPath = queueItem.pathButtons.slice(0..<queueItem.pathButtons.lastIndex)
            val grandparentPathUsedButtons = grandparentPathToUsedButtons[grandparentPath] ?: listOf()

            machineSpecification.buttons.filter {
                !queueItem.pathButtons.contains(it) && !grandparentPathUsedButtons.contains(it)
            }.forEach { button ->
                val newQueueItem = BfsQueueItem(
                    parent = button,
                    device = queueItem.device.withAffectedIndices(*button.affectedIndices.toIntArray()),
                    pathButtons = queueItem.pathButtons + listOf(button),
                )

                queue.add(newQueueItem)
            }

            if (queueItem.pathButtons.isNotEmpty()) {
                val parent = queueItem.pathButtons.last()

                grandparentPathToUsedButtons.getOrPut(grandparentPath) { mutableListOf() }.add(parent)
            }
        }

        throw IllegalStateException("Solution not found")
    }
}
