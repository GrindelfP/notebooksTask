package to.deepstorage.notebooksTask

/**
 * Object class for different methods of decision-making.
 */
object DecisionMaker {
    /**
     * Generates Pareto's set of given dataset, and returns it.
     */
    fun paretoProcessor(dataSet: DataSet): DataSet {
        val paretoSet: MutableSet<Entry> = mutableSetOf()
        for (entry in dataSet.entries) {
            var isParetoOptimal = true
            for (other in paretoSet) {
                if (other.dominates(entry)) {
                    isParetoOptimal = false
                    break
                }
                if (entry.dominates(other)) paretoSet.remove(other)
            }
            if (isParetoOptimal) paretoSet.add(entry)
        }

        return DataSet(paretoSet.toList())
    }

    /**
     * Narrows given dataset by comparing it to [lowestCriteria] parameter.
     */
    fun byLowestCriteria(dataSet: DataSet, lowestCriteria: Entry): DataSet {
        val narrowedSet: MutableSet<Entry> = mutableSetOf()
        dataSet.entries.forEach { entry ->
            if (entry >= lowestCriteria) narrowedSet.add(entry)
        }

        return DataSet(narrowedSet.toList())
    }

    /**
     * Generates result based on [narrowedSet] as well as maximisation of the chosen criteria.
     */
    fun subOptimisationMethod(narrowedSet: DataSet): Entry = narrowedSet.entries.maxBy { it.graphicsCardModel }

    /**
     * Generates result based on maximizing ordered criterion.
     */
    fun lexicographicalMethod(dataSet: DataSet): Entry {
        dataSet.sortDescending()
        return dataSet.entries[0]
    }

    /**
     * Generates result based on maximizing the combined criteria
     */
    fun combinedCriteriaProcessor(dataSet: DataSet): Entry {
        val dataSetWithCriteria = mutableMapOf<Double, Entry>()
        dataSet.entries.forEach {
            dataSetWithCriteria[it.getCombinedCriteria()] = it
        }

        return dataSetWithCriteria.maxBy { it.key }.value
    }

    /**
     * Checks if the [other] instance of [Entry] is dominated by [this] instance of [Entry].
     */
    private fun Entry.dominates(other: Entry): Boolean = this.hasAtLeastOneBetterValueThan(other) && this >= other

    /**
     * Sorts [DataSet.entries] by price, then by core memory size, graphical memory size,
     * drive size, graphics card model, screen resolution, weight, screed diagonal,
     * battery capacity and design mark.
     */
    private fun DataSet.sortDescending() {
        entries = entries.sortedWith(compareBy<Entry> { it.price }.thenBy { -it.coreMemorySize }
            .thenBy { -it.graphicalMemorySize }.thenBy { -it.driveSize }.thenBy { -it.graphicsCardModel }
            .thenBy { -it.screenResolution }.thenBy { it.weight }.thenBy { -it.screenDiagonal }
            .thenBy { -it.batteryCapacity }.thenBy { -it.designMark.value }
        )
    }

    /**
     * Generates combined criteria of an [Entry] instance.
     */
    private fun Entry.getCombinedCriteria(): Double = (0.05 * coreMemorySize +
            0.05 * driveSize + 0.3 * graphicalMemorySize + 0.05 * (-price) + 0.05 * screenDiagonal +
            0.05 * screenResolution.totalPixels + graphicsCardModel * 0.3 + 0.05 * (-weight) + 0.05 * batteryCapacity
            + 0.05 * designMark.value)

    /**
     * Operator-function supporting multiplication operation between a [GraphicsCardModel]
     * instance and a [Double] instance.
     */
    private operator fun GraphicsCardModel.times(factor: Double) = factor * (number +
            when (prefix) {
                "M1PRO" -> 4
                "M1" -> 3
                "GTX" -> 2
                "RTX" -> 1
                else -> 0
            })
}
