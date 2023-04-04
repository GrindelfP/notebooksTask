package to.deepstorage.notebooksTask

data class ScreenResolution(
    val horizontal: Int,
    val vertical: Int
) : Comparable<ScreenResolution> {
    val totalPixels: Double = horizontal * vertical * 1e-5

    override fun toString(): String {
        return horizontal.toString() + "x" + vertical.toString()
    }

    override fun equals(other: Any?): Boolean = when (other) {
        is ScreenResolution -> horizontal == other.horizontal && vertical == other.vertical
        else -> false
    }

    override fun hashCode(): Int {  // ask Sasha what happens in the default case here
        return super.hashCode()
    }

    override operator fun compareTo(other: ScreenResolution) : Int = when {
        totalPixels == other.totalPixels -> 0
        totalPixels > other.totalPixels -> 1
        else -> -1
    }

    operator fun unaryMinus(): Double = -totalPixels
}
