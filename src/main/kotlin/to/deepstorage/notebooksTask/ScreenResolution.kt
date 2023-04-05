package to.deepstorage.notebooksTask

/**
 * Data class representing the screen resolution.
 */
data class ScreenResolution(
    val horizontal: Int,
    val vertical: Int
) : Comparable<ScreenResolution> {
    /**
     * Total number of pixels of the screen.
     */
    val totalPixels: Double = horizontal * vertical * 1e-5

    /**
     * Overrides [Any.toString] function.
     */
    override fun toString(): String {
        return horizontal.toString() + "x" + vertical.toString()
    }

    /**
     * Overrides [Any.equals] function.
     */
    override fun equals(other: Any?): Boolean = when (other) {
        is ScreenResolution -> horizontal == other.horizontal && vertical == other.vertical
        else -> false
    }

    /**
     * Overrides [Any.hashCode] function.
     */
    override fun hashCode(): Int {
        return super.hashCode()
    }

    /**
     * Overrides [Comparable.compareTo] function.
     */
    override operator fun compareTo(other: ScreenResolution) : Int = when {
        totalPixels == other.totalPixels -> 0
        totalPixels > other.totalPixels -> 1
        else -> -1
    }

    /**
     * Operator-function supporting unary minus operator for a [ScreenResolution] instance.
     */
    operator fun unaryMinus(): Double = -totalPixels
}
