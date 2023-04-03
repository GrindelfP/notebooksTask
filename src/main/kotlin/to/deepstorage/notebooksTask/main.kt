package to.deepstorage.notebooksTask

fun main() {
    var data = DataPreprocessor.extractData()
    data.print("Initial dataset:")

    // generating Pareto's set
    data = DecisionMaker.paretoProcessor(data.copy())
    data.print("Pareto's set:")

    // narrowing Pareto's set by comparing to the lowest criteria
    val lowestCriteria: Entry = Entry(0, "lowestCriteria", 16, 512,0,
        1600000, 14.0, ScreenResolution(1920, 1080),
        GraphicsCardModel("GTX", 0), 2400, 40.0, DesignMark.GOOD )
    data = DecisionMaker.byLowestCriteria(data.copy(), lowestCriteria)
    data.print("Narrowed Pareto's set by comparing to the lowest criteria:")

    /*resultsOfDecisionMaking.add(DecisionMaker.subOptimisationMethod(data))
    resultsOfDecisionMaking.add(DecisionMaker.lexicographicalMethod(data))
    resultsOfDecisionMaking.add(DecisionMaker.commonCriteriaProcessor(data))*/
}

fun DataSet.print(header: String) {
    println("\n$header")
    entries.forEach {
        println(it)
    }
}
