package to.deepstorage.notebooksTask

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

/**
 * Class used to extract instance of [DataSet] from .json file.
 */
class DataPreprocessor(
    private val pathToData: String
) {
    /**
     * An instance of a [ObjectMapper] with registered [kotlinModule()].
     */
    private val mapperModule: ObjectMapper = ObjectMapper().registerModule(kotlinModule())

    /**
     * Reads .json-formatted [String] value and returns its data as a [DataSet] instance.
     */
    fun extractData(): DataSet = mapperModule.readValue(readFile())

    /**
     * Reads a text file and returns it as a single [String] instance.
     */
    private fun readFile(): String = File(pathToData).readText()
}
