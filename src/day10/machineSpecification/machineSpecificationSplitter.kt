package day10.machineSpecification

private const val MACHINE_SPECIFICATION_SEPARATOR = " "

fun splitMachineSpecification(line: String): List<String> {
    return line.split(MACHINE_SPECIFICATION_SEPARATOR)
}
