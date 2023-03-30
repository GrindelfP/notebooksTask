package to.deepstorage.notebooksTask

fun main() {
    val resultsOfDecisionMaking = mutableListOf<DataSet>()
    val data = DataProcessor.processData()
    data.entries.forEach { println(it) }
    DecisionMaker.paretoProcessor(data)


    /*resultsOfDecisionMaking.add(DecisionMaker.lowestCriteria(data))
    resultsOfDecisionMaking.add(DecisionMaker.subOptimisationMethod(data))
    resultsOfDecisionMaking.add(DecisionMaker.lexicographicalMethod(data))
    resultsOfDecisionMaking.add(DecisionMaker.commonCriteriaProcessor(data))*/

    resultsOfDecisionMaking.forEach {
        println(/*printing the best*/)
    }
}
