package to.deepstorage.notebookstask.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

object DataPreprocessing {

    /**
     * An instance of a [ObjectMapper] with registered [kotlinModule()].
     */
    private fun objectMapper(): ObjectMapper = ObjectMapper().registerModule(kotlinModule())
    val deserializer = objectMapper()

    /**
     * DESCRIPTION. NOTES...
     *
     * @param absolutPath - absolute path to json file with data
     *
     * @return - given type of data deserialized from the file with [absolutPath]
     */
    inline fun <reified T> extractDataFromFile(absolutPath: String): T {
        // 1. read file
        val dataAsText = File(absolutPath).readText()
        // 2. deserialize bytes to T
        return deserializer.readValue(dataAsText)
    }
}