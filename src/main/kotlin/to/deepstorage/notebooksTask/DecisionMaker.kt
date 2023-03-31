package to.deepstorage.notebooksTask

object DecisionMaker {

    fun paretoProcessor(dataSet: DataSet): DataSet {
        dataSet.sortDescending()
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

    fun lowestCriteria(dataSet: DataSet): DataSet {
        throw NotImplementedError();
    }

    fun subOptimisationMethod(dataSet: DataSet): DataSet {
        throw NotImplementedError();
    }

    fun lexicographicalMethod(dataSet: DataSet): DataSet {
        throw NotImplementedError();
    }

    fun commonCriteriaProcessor(dataSet: DataSet): DataSet {
        throw NotImplementedError();
    }

    private fun Entry.dominates(other: Entry): Boolean = this.hasAtLeastOneBetterValueThen(other) && this >= other

    private fun DataSet.sortDescending() {
        entries = entries.sortedWith(compareBy<Entry> { it.price }.
        thenBy { -it.coreMemorySize }.
        thenBy { -it.graphicalMemorySize }.
        thenBy { -it.driveSize }.
        thenBy { -it.graphicsCardModel }.
        thenBy { -it.screenResolution }.
        thenBy { it.weight }.
        thenBy { -it.screenDiagonal }.
        thenBy { -it.batteryCapacity }.
        thenBy { -it.designMark.value }
        )
    }
}
