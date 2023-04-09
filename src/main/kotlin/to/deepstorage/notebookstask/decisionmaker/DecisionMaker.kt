package to.deepstorage.notebookstask.decisionmaker

import to.deepstorage.notebookstask.domain.NotebookSet
import to.deepstorage.notebookstask.domain.Notebook
import to.deepstorage.notebookstask.domain.GraphicsCardModel

/**
 * Object class for different methods of decision-making.
 */
object DecisionMaker {
    /**
     * Generates Pareto's set of given dataset, and returns it.
     */
    fun paretoProcessor(notebookSet: NotebookSet): NotebookSet {
        val paretoSet: MutableSet<Notebook> = mutableSetOf()
        for (entry in notebookSet.notebooks) {
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

        return NotebookSet(notebooks = paretoSet.toList(), description = "Pareto's set")
    }

    /**
     * Narrows given dataset by comparing it to [lowestCriteria] parameter.
     */
    fun byLowestCriteria(notebookSet: NotebookSet, lowestCriteria: Notebook): NotebookSet {
        val narrowedSet: MutableSet<Notebook> = mutableSetOf()
        notebookSet.notebooks.forEach { entry ->
            if (entry >= lowestCriteria) narrowedSet.add(entry)
        }

        return NotebookSet(
                notebooks = narrowedSet.toList(),
                description = "Narrowed Pareto's set by comparing to the lowest criteria"
        )
    }

    /**
     * Generates result based on [narrowedSet] as well as maximisation of the chosen criteria.
     */
    fun subOptimisationMethod(narrowedSet: NotebookSet): NotebookSet = NotebookSet(
            notebooks = listOf(narrowedSet.notebooks.maxBy { it.graphicsCardModel }),
            description = "After sub-optimisation the best is"
    )

    /**
     * Generates result based on maximizing ordered criterion.
     */
    fun lexicographicalMethod(notebookSet: NotebookSet): NotebookSet {
        val sortedEntries = notebookSet.sortDescending()
        return NotebookSet(
                notebooks = listOf(sortedEntries[0]),
                description = "After applying lexicographical method " +
                        "(where most important criteria are: price, core memory size, graphics memory size etc.) " +
                        "the best is"
        )
    }

    /**
     * Generates result based on maximizing the combined criteria
     */
    fun combinedCriteriaProcessor(notebookSet: NotebookSet): NotebookSet {
        val dataSetWithCriteria = mutableMapOf<Double, Notebook>()
        notebookSet.notebooks.forEach {
            dataSetWithCriteria[it.getCombinedCriteria()] = it
        }

        return NotebookSet(
                notebooks = listOf(dataSetWithCriteria.maxBy { it.key }.value),
                description = "After applying common criteria method the best is"
        )
    }

    /**
     * Checks if the [other] instance of [Notebook] is dominated by [this] instance of [Notebook].
     */
    private fun Notebook.dominates(other: Notebook): Boolean = this.hasAtLeastOneBetterValueThan(other) && this >= other

    /**
     * Sorts [NotebookSet.notebooks] by price, then by core memory size, graphical memory size,
     * drive size, graphics card model, screen resolution, weight, screed diagonal,
     * battery capacity and design mark.
     */
    private fun NotebookSet.sortDescending(): List<Notebook> {
        return notebooks.sortedWith(compareBy<Notebook> { it.price }.thenBy { -it.coreMemorySize }
                .thenBy { -it.graphicalMemorySize }.thenBy { -it.driveSize }.thenBy { -it.graphicsCardModel }
                .thenBy { -it.screenResolution }.thenBy { it.weight }.thenBy { -it.screenDiagonal }
                .thenBy { -it.batteryCapacity }.thenBy { -it.designMark.value }
        )
    }

    /**
     * Generates combined criteria of an [Notebook] instance.
     */
    private fun Notebook.getCombinedCriteria(): Double = (0.05 * coreMemorySize +
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
