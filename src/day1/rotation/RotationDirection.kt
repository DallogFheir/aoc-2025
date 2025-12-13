package day1.rotation

enum class RotationDirection {
    LEFT,
    RIGHT;

    companion object {
        fun fromString(string: String): RotationDirection {
            return when (string) {
                "L" -> LEFT
                "R" -> RIGHT
                else -> throw IllegalArgumentException("Invalid rotation direction string: $string")
            }
        }
    }
}
