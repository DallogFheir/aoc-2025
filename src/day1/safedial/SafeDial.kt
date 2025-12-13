package day1.safedial

import day1.rotation.Rotation

class SafeDial(
    private val size: Int,
    startingPoint: Int
) {
    var currentPoint: Int = startingPoint
        private set

    fun rotate(rotation: Rotation) {
        currentPoint = (currentPoint + rotation.toInt()) % size
    }
}
