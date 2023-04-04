package to.deepstorage.notebooksTask

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

    operator fun unaryMinus(): GraphicsCardModel {
        val newPrefix: String = when (prefix) {
            "M1" -> "RTX"
            "M1PRO" -> "GTX"
            "GTX" -> "M1"
            "RTX" -> "M1PRO"
            else -> throw Exception("Initialized with wrong value of prefix!")
        }
        return GraphicsCardModel(newPrefix, -number)
    }
}
