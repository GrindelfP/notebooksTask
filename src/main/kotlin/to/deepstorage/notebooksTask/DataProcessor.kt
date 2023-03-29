package to.deepstorage.notebooksTask

import java.io.File
import kotlin.Exception

object DataProcessor {
    private const val pathToData: String = "/home/gregory/MEGA/programming/kotlin/" +
            "DecisionTheory_MultiObjectiveOptimisation/dataset/laptops_data"
    fun processData(): List<Entry> {
        val data: MutableList<Entry> = mutableListOf()
        readFile().forEach {
            val partedString: List<String> = it.split('|').toList()
            data.add(
                Entry(
                    partedString[0],
                    partedString[1].toInt(),
                    partedString[2].toInt(),
                    partedString[3].toInt(),
                    partedString[4].toInt(),
                    partedString[5].toDouble(),
                    ScreenResolution(
                        partedString[6].substringBefore('x').toInt(),
                        partedString[6].substringAfter('x').toInt()
                    ),
                    GraphicsCardModel(
                        partedString[7].substringBefore('-'),
                        partedString[7].substringAfter('-').toInt()
                    ),
                    partedString[8].toInt(),
                    partedString[9].toDouble(),
                    when (partedString[10].toInt()) {
                        0 -> DesignMark.POOR
                        1 -> DesignMark.GOOD
                        2 -> DesignMark.EXCELLENT
                        else -> throw Exception("The value of design mark is wrong")
                    }
                )
            )
        }

        return data
    }

    private fun readFile(): List<String> = File(pathToData).readLines()
}