package to.deepstorage.notebooksTask

/**
 * DataSet class contains the list of entries and operator to get them by their id.
 */
data class DataSet(
    var entries: List<Entry>
) {
    /**
     * Operator function which returns an instance of Entry type from the container which
     * has the same 'id' property as provided as a parameter [index]. Because it is an operator
     * it can be called this way dataSetInstance[[index]] or dataSetInstance.get([index])
    */
    operator fun get(index: Int): Entry {
        entries.forEach {
            if (it.id == index) return it
        }
        throw Exception("There is no such entry")
    }
}
