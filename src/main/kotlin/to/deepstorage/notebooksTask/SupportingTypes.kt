package to.deepstorage.notebooksTask

data class ScreenResolution(
    val horizontal: Int,
    val vertical: Int
) : Comparable<ScreenResolution> {
    private val totalPixels: Int = horizontal * vertical

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
}

data class GraphicsCardModel(
    val prefix: String,
    val number: Int
) : Comparable<GraphicsCardModel> {
    override fun equals(other: Any?): Boolean = when (other) {
        is GraphicsCardModel -> this.prefix == other.prefix && this.number == other.number
        else -> false
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override operator fun compareTo(other: GraphicsCardModel): Int = when (prefix) {
        other.prefix ->
            if (number < other.number) -1 else if (number == other.number) 0 else 1
        "M1PRO" -> 1
        "M1" -> if (other.prefix == "M1PRO") -1 else 1
        "GTX" -> if (other.prefix.contains("M1")) -1 else 1
        else -> -1
    }
}
