package day10

import com.google.ortools.Loader
import com.google.ortools.sat.CpModel
import com.google.ortools.sat.CpSolver
import com.google.ortools.sat.CpSolverStatus
import com.google.ortools.sat.LinearExpr
import day10.button.parseButtons
import day10.device.JoltageCounter
import day10.machineSpecification.JoltageCounterMachineSpecification
import day10.machineSpecification.splitMachineSpecification
import utils.filereader.FileReader

private const val JOLTAGE_COUNTER_PREFIX = "{"
private const val JOLTAGE_COUNTER_SUFFIX = "}"
private const val JOLTAGE_COUNTER_SEPARATOR = ","

object Part2 {
    fun solve(dayNumber: Int, fileName: String): Long {
        Loader.loadNativeLibraries()

        val machineSpecifications = FileReader(dayNumber = dayNumber, fileName = fileName).readLinesWithParser { line ->
            val lineParts = splitMachineSpecification(line)

            val joltageCounterString =
                lineParts.last().removePrefix(JOLTAGE_COUNTER_PREFIX).removeSuffix(JOLTAGE_COUNTER_SUFFIX)

            val joltages =
                joltageCounterString.split(JOLTAGE_COUNTER_SEPARATOR).map { it.toInt() }.toTypedArray()

            val joltageCounter = JoltageCounter(joltages = joltages)

            val buttons = parseButtons(lineParts)

            JoltageCounterMachineSpecification(
                joltageCounter = joltageCounter,
                buttons = buttons,
            )
        }

        return machineSpecifications.sumOf { specification ->
            val coefficients = (0..<specification.joltageCounter.size).map { index ->
                specification.buttons.map { button ->
                    if (index in button.affectedDeviceIndices) 1L else 0L
                }
            }
            val variableCount = coefficients.first().size

            val rightHandSides = specification.joltageCounter.joltages.map { it.toLong() }

            val model = CpModel()

            val upperBound = rightHandSides.sum()
            val variables = Array(variableCount) { index ->
                model.newIntVar(0L, upperBound, "x${index + 1}")
            }

            coefficients.zip(rightHandSides).forEach { (leftHandSide, rightHandSide) ->
                val expression = LinearExpr.weightedSum(
                    variables,
                    leftHandSide.toLongArray(),
                )

                model.addEquality(expression, rightHandSide)
            }

            model.minimize(
                LinearExpr.sum(variables)
            )

            val solver = CpSolver()
            val status = solver.solve(model)

            if (status != CpSolverStatus.OPTIMAL) {
                throw IllegalStateException("Solver finished with status $status")
            }

            val values = variables.map { solver.value(it) }

            values.sum()
        }
    }
}
