package to.deepstorage.notebooksTask

fun main() {
    val data = DataPreprocessor.extractData()
    data.print("Initial dataset:")

    // generating Pareto's set
    val dataByPareto = DecisionMaker.paretoProcessor(data.copy())
    dataByPareto.print("Pareto's set:")

    // narrowing Pareto's set by comparing to the lowest criteria
    val lowestCriteria: Entry = Entry(0, "lowestCriteria", 16, 512,0,
        170000, 14.0, ScreenResolution(1920, 1080),
        GraphicsCardModel("GTX", 0), 2400, 40.0, DesignMark.GOOD )
    val dataByLowestCriteria = DecisionMaker.byLowestCriteria(dataByPareto.copy(), lowestCriteria)
    dataByLowestCriteria.print("Narrowed Pareto's set by comparing to the lowest criteria:")

    // sub-optimisation method performing
    val subOptimisedResult = DecisionMaker.subOptimisationMethod(data)
    println("After sub-optimisation the best is: \n${subOptimisedResult}\n")

    // using lexicographical method
    val lexicographicalResult = DecisionMaker.lexicographicalMethod(data)
    println("After applying lexicographical method (where most important criteria are: price, core memory size, " +
            "graphics memory size etc.) the best is: \n${lexicographicalResult}\n")

    // using common criteria method
    val commonCriteriaResult = DecisionMaker.commonCriteriaProcessor(data)
    println("After applying common criteria method the best is: \n${commonCriteriaResult}\n")
}

fun DataSet.print(header: String) {
    println(header)
    entries.forEach {
        println(it)
    }
    println("\n")
}
