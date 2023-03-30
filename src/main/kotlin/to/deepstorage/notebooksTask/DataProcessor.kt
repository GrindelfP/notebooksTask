package to.deepstorage.notebooksTask

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

object DataProcessor {
    private const val pathToData: String = "/home/gregory/MEGA/programming/kotlin/" +
            "DecisionTheory_MultiObjectiveOptimisation/dataset/laptop_data.json"
    fun processData(): DataSet = ObjectMapper().registerModule(kotlinModule()).readValue(readFile())

    private fun readFile(): String = File(pathToData).readText()
}
