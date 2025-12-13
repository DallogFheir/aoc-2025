package day1.safedial

import day1.rotation.Rotation
import day1.rotation.RotationDirection
import kotlin.math.abs

class SafeDial(
    private val size: Int,
    startingPoint: Int
) {
    var currentPoint = startingPoint
        private set

    var pointedAtZeroCount = 0
        private set

    fun rotate(rotation: Rotation) {
        val newPoint = currentPoint + rotation.toInt()

        val fullRotationPassedZeroCount = abs(newPoint / size)
        val didPassZeroCounterclockwise = doesPassZeroCounterclockwise(rotation)
        val passedZeroCount = fullRotationPassedZeroCount + if (didPassZeroCounterclockwise) 1 else 0
        pointedAtZeroCount += passedZeroCount

        currentPoint = Math.floorMod(newPoint, size)
    }

    private fun doesPassZeroCounterclockwise(rotation: Rotation): Boolean {
        return currentPoint != 0 && currentPoint <= rotation.step && rotation.direction == RotationDirection.LEFT
    }
}
