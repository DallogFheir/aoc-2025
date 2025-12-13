package day1.rotation

data class Rotation(val direction: RotationDirection, val step: Int) {
    fun toInt(): Int {
        val signMultiplier = when (direction) {
            RotationDirection.LEFT -> -1
            RotationDirection.RIGHT -> 1
        }

        return step * signMultiplier
    }

    companion object {
        fun fromString(string: String): Rotation {
            val patternMatch =
                ROTATION_PATTERN.find(string) ?: throw IllegalArgumentException("Invalid string: $string")

            val directionStr = patternMatch.groups[1]!!.value
            val direction = RotationDirection.fromString(directionStr)

            val stepStr = patternMatch.groups[2]!!.value
            val step = stepStr.toInt()

            return Rotation(direction = direction, step = step)
        }
    }
}
