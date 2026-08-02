package day10.button

private const val BUTTON_PREFIX = "("
private const val BUTTON_SUFFIX = ")"
private const val BUTTON_LIGHT_SEPARATOR = ","

fun parseButtons(lineParts: List<String>): List<Button> {
    val buttonStrings = lineParts.slice(1..<lineParts.lastIndex)

    return buttonStrings.map { buttonString ->
        val buttonDeviceStrings =
            buttonString.removePrefix(BUTTON_PREFIX).removeSuffix(BUTTON_SUFFIX).split(BUTTON_LIGHT_SEPARATOR)

        val buttonDevices = buttonDeviceStrings.map { it.toInt() }.toSet()

        Button(affectedDeviceIndices = buttonDevices)
    }
}
