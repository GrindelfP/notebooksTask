package to.deepstorage.notebookstask.utils

import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import to.deepstorage.notebookstask.utils.DataPreprocessing.extractDataFromFile
import kotlin.io.path.absolutePathString
import kotlin.io.path.writeText

class DataPreprocessingTest {

    @Test
    fun `GIVEN a json file WHEN deserializing it to an object THEN correct object return`() {
        @Language("JSON")
        val serializedBart = """
            {
              "name": "Bart",
              "surname": "Simpson",
              "age": 48
            }
        """.trimIndent()
        val file = kotlin.io.path.createTempFile()
        file.writeText(serializedBart)

        val deserializedBart: Person = extractDataFromFile(file.absolutePathString())
        assertEquals(deserializedBart, Person(name = "Bart", surname = "Simpson", age = 48))
    }
}

data class Person(val name: String, val surname: String, val age: Int)