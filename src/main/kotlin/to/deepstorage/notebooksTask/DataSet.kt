package to.deepstorage.notebooksTask

data class DataSet(
    var entries: List<Entry>
) {
    operator fun get(index: Int): Entry {
        entries.forEach {
            if (it.id == index) return it
        }
        throw Exception("There is no such entry")
    }
}
