package to.deepstorage.notebooksTask

data class Entry (
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

    override fun hashCode(): Int {
        return super.hashCode()
    }
    
    operator fun compareTo(other: Entry): Int = when {
        this == other -> 0
        coreMemorySize > other.coreMemorySize &&
        driveSize > other.driveSize &&
        graphicalMemorySize > other.graphicalMemorySize &&
        price < other.price &&
        screenDiagonal > other.screenDiagonal &&
        screenResolution > other.screenResolution &&
        graphicsCardModel > other.graphicsCardModel &&
        weight < other.weight &&
        batteryCapacity > other.batteryCapacity &&
        designMark > other.designMark -> 1
        else -> -1
    } 
}

enum class DesignMark(val value: Int) {
    POOR(0),
    GOOD(1),
    EXCELLENT(2)
}
