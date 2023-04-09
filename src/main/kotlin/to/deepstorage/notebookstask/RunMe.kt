package to.deepstorage.notebookstask

import to.deepstorage.notebookstask.decisionmaker.DecisionMaker
import to.deepstorage.notebookstask.domain.*
import to.deepstorage.notebookstask.utils.DataPreprocessing.extractDataFromFile
import java.util.Properties

object RunMe {

    private val configsStream = RunMe::class.java.getResourceAsStream("/configurations.conf")!!
    private val properties = Properties().apply {
        load(configsStream)
    }
    private val pathToData = properties.getProperty("absolutePathToDataSetFile")

    private val LOWEST_CRITERIA = Notebook(0, "lowestCriteria", 16, 512,0,
            170000, 14.0, ScreenResolution(1920, 1080),
            GraphicsCardModel("GTX", 0), 2400, 40.0, DesignMark.GOOD )

    @JvmStatic
    fun main(args: Array<String>) {
        val data: NotebookSet = extractDataFromFile(absolutPath = pathToData)
        println(data)

        // generating Pareto's set
        val dataByPareto = DecisionMaker.paretoProcessor(data.copy())
        println(dataByPareto)

        // narrowing Pareto's set by comparing to the lowest criteria
        val dataByLowestCriteria = DecisionMaker.byLowestCriteria(dataByPareto.copy(), LOWEST_CRITERIA)
        println(dataByLowestCriteria)

        // sub-optimisation method performing
        val subOptimisedResult = DecisionMaker.subOptimisationMethod(data)
        println(subOptimisedResult)

        // using lexicographical method
        val lexicographicalResult = DecisionMaker.lexicographicalMethod(data)
        println(lexicographicalResult)

        // using common criteria method
        val commonCriteriaResult = DecisionMaker.combinedCriteriaProcessor(data)
        println(commonCriteriaResult)
    }
}
