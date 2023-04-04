package to.deepstorage.notebooksTask

object DecisionMaker {

    fun paretoProcessor(dataSet: DataSet): DataSet {
        //dataSet.sortDescending()
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

    fun byLowestCriteria(dataSet: DataSet, lowestCriteria: Entry): DataSet {
        val narrowedSet: MutableSet<Entry> = mutableSetOf()
        dataSet.entries.forEach { entry ->
            if (entry >= lowestCriteria) narrowedSet.add(entry)
        }

        return DataSet(narrowedSet.toList())
    }

    fun subOptimisationMethod(narrowedSet: DataSet): Entry = narrowedSet.entries.maxBy { it.graphicsCardModel }

    fun lexicographicalMethod(dataSet: DataSet): Entry {
        dataSet.sortDescending()
        return dataSet.entries[0]
    }

    fun commonCriteriaProcessor(dataSet: DataSet): Entry {
        val dataSetWithCriteria = mutableMapOf<Double, Entry>()
        dataSet.entries.forEach {
            dataSetWithCriteria[it.getCommonCriteria()] = it
        }

        return dataSetWithCriteria.maxBy { it.key }.value
    }

    private fun Entry.dominates(other: Entry): Boolean = this.hasAtLeastOneBetterValueThen(other) && this >= other

    private fun DataSet.sortDescending() {
        entries = entries.sortedWith(compareBy<Entry> { it.price }.thenBy { -it.coreMemorySize }
            .thenBy { -it.graphicalMemorySize }.thenBy { -it.driveSize }.thenBy { -it.graphicsCardModel }
            .thenBy { -it.screenResolution }.thenBy { it.weight }.thenBy { -it.screenDiagonal }
            .thenBy { -it.batteryCapacity }.thenBy { -it.designMark.value }
        )
    }

    private fun Entry.getCommonCriteria(): Double = 0.1 * (coreMemorySize +
            driveSize + graphicalMemorySize + price + screenDiagonal + screenResolution.totalPixels
            + graphicsCardModel + weight + batteryCapacity + designMark.value)
}

private operator fun Double.plus(graphicsCardModel: GraphicsCardModel): Int = graphicsCardModel.number +
        when (graphicsCardModel.prefix) {
            "M1PRO" -> 4
            "M1" -> 3
            "GTX" -> 2
            "RTX" -> 1
            else -> 0
        }
