package to.deepstorage.notebooksTask

/**
 * Data class representing the information about a laptop. Its id, name,
 * core memory size, drive size, graphical memory size, price, screen diagonal,
 * screen resolution, graphics card model, weight, battery capacity and design mark.
 */
data class Entry(
    val id: Int,
    val name: String,
    val coreMemorySize: Int,
    val driveSize: Int,
    val graphicalMemorySize: Int,
    val price: Int,
    val screenDiagonal: Double,
    val screenResolution: ScreenResolution,
    val graphicsCardModel: GraphicsCardModel,
    val weight: Int,
    val batteryCapacity: Double,
    val designMark: DesignMark
) {

    /**
     * Overrides [Any.equals] function.
     */
    override fun equals(other: Any?): Boolean = when (other) {
        is Entry ->
            name == other.name &&
                    coreMemorySize == other.coreMemorySize &&
                    driveSize == other.driveSize &&
                    graphicalMemorySize == other.graphicalMemorySize &&
                    price == other.price &&
                    screenDiagonal == other.screenDiagonal &&
                    screenResolution == other.screenResolution &&
                    graphicsCardModel == other.graphicsCardModel &&
                    weight == other.weight &&
                    batteryCapacity == other.batteryCapacity &&
                    designMark == other.designMark

        else -> false
    }

    /**
     * Overrides [Any.hashCode] function.
     */
    override fun hashCode(): Int {
        return super.hashCode()
    }

    /**
     * Operator-function comparing [this] instance of [Entry] to the [other]
     * instance of [Entry]. It returns 0 when they are equal, -1 when the [other]
     * is greater than [this] and 1 if [this] is greater than [other].
     */
    operator fun compareTo(other: Entry): Int = when {
        this == other || equalOrGreaterThan(other) || equalOrLesserThan(other) -> 0
        hasOnlyGreaterValuesThan(other) -> 1
        else -> -1
    }

    /**
     * Checks if [this] instance of [Entry] has at least one better value than
     * the [other] instance of [Entry].
     */
    fun hasAtLeastOneBetterValueThan(other: Entry): Boolean =
        coreMemorySize > other.coreMemorySize ||
                driveSize > other.driveSize ||
                graphicalMemorySize > other.graphicalMemorySize ||
                price < other.price ||
                screenDiagonal > other.screenDiagonal ||
                screenResolution > other.screenResolution ||
                graphicsCardModel > other.graphicsCardModel ||
                weight < other.weight ||
                batteryCapacity > other.batteryCapacity ||
                designMark > other.designMark

    /**
     * Checks if [this] instance of [Entry] has at only greater values than
     * the [other] instance of [Entry].
     */
    private fun hasOnlyGreaterValuesThan(other: Entry): Boolean =
        coreMemorySize > other.coreMemorySize &&
                driveSize > other.driveSize &&
                graphicalMemorySize > other.graphicalMemorySize &&
                price < other.price &&
                screenDiagonal > other.screenDiagonal &&
                screenResolution > other.screenResolution &&
                graphicsCardModel > other.graphicsCardModel &&
                weight < other.weight &&
                batteryCapacity > other.batteryCapacity &&
                designMark > other.designMark

    /**
     * Checks if [this] instance of [Entry] has equal or greater values than
     * the [other] instance of [Entry].
     */
    private fun equalOrGreaterThan(other: Entry): Boolean =
        coreMemorySize >= other.coreMemorySize &&
                driveSize >= other.driveSize &&
                graphicalMemorySize >= other.graphicalMemorySize &&
                price <= other.price &&
                screenDiagonal >= other.screenDiagonal &&
                screenResolution >= other.screenResolution &&
                graphicsCardModel >= other.graphicsCardModel &&
                weight <= other.weight &&
                batteryCapacity >= other.batteryCapacity &&
                designMark >= other.designMark
    /**
     * Checks if [this] instance of [Entry] has equal or lesser values than
     * the [other] instance of [Entry].
     */
    private fun equalOrLesserThan(other: Entry): Boolean =
        coreMemorySize <= other.coreMemorySize &&
                driveSize <= other.driveSize &&
                graphicalMemorySize <= other.graphicalMemorySize &&
                price >= other.price &&
                screenDiagonal <= other.screenDiagonal &&
                screenResolution <= other.screenResolution &&
                graphicsCardModel <= other.graphicsCardModel &&
                weight >= other.weight &&
                batteryCapacity <= other.batteryCapacity &&
                designMark <= other.designMark
}
