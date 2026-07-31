package day10.machineSpecification

data class IndicatorLights(val lights: Array<Boolean>) {
    val size = lights.size

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other !is IndicatorLights) {
            return false
        }

        return lights.contentEquals(other.lights)
    }

    override fun hashCode(): Int {
        return lights.contentHashCode()
    }

    fun withToggled(vararg indices: Int): IndicatorLights {
        val newLights = lights.copyOf()

        indices.forEach {
            require(it < lights.size) { "Index $it out of range for lights of size ${lights.size}" }

            newLights[it] = !newLights[it]
        }

        return IndicatorLights(lights = newLights)
    }

    companion object {
        fun emptyOfLength(length: Int): IndicatorLights {
            return IndicatorLights(Array(length) { false })
        }
    }
}
