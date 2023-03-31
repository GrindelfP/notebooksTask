package to.deepstorage.notebooksTask

fun main() {
    var data = DataProcessor.processData()
    data.print("Initial dataset:")
    // generating Pareto's set
    data = DecisionMaker.paretoProcessor(data)
    data.print("Pareto's set:")


    /*resultsOfDecisionMaking.add(DecisionMaker.lowestCriteria(data))
    resultsOfDecisionMaking.add(DecisionMaker.subOptimisationMethod(data))
    resultsOfDecisionMaking.add(DecisionMaker.lexicographicalMethod(data))
    resultsOfDecisionMaking.add(DecisionMaker.commonCriteriaProcessor(data))*/
}

fun DataSet.print(header: String) {
    println("\n$header")
    entries.forEach {
        println(it)
    }
}
