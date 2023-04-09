package to.deepstorage.notebookstask.domain

/**
 * DataSet class contains the list of entries and operator to get them by their id.
 */
data class NotebookSet(val description: String, val notebooks: List<Notebook>) {
    /**
     * Operator function which returns an instance of Entry type from the container which
     * has the same 'id' property as provided as a parameter [index]. Because it is an operator
     * it can be called this way dataSetInstance[[index]] or dataSetInstance.get([index])
    */
    operator fun get(index: Int): Notebook =
            requireNotNull(notebooks.firstOrNull { entry -> entry.id == index }) { "There is no such entry" }

    override fun toString(): String {
        var result = "$description:\n"
        notebooks.forEach {
            result += it.toString() + "\n"
        }
        return "$result\n"
    }
}
