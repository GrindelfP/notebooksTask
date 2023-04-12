package to.deepstorage.notebookstask.decisionmaker

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import to.deepstorage.notebookstask.decisionmaker.DecisionMaker.paretoProcessor
import to.deepstorage.notebookstask.domain.*

class DecisionMakerTest {

    @Test
    fun `GIVEN valid data set WHEN creating pareto set THEN expected result returned`() {
        val dataSet = NotebookSet(
                description = "test",
                notebooks = listOf(
                        Notebook(
                                2,
                                "Asus Tuf Gaming F15",
                                16,
                                512,
                                4,
                                64000,
                                15.6,
                                ScreenResolution(1920, 1080),
                                GraphicsCardModel("GTX", 1650),
                                2300,
                                48.00,
                                DesignMark.EXCELLENT
                        ),
                        Notebook(
                                3,
                                "Ardor Gaming Neo G15-I5ND200",
                                16,
                                512,
                                4,
                                65000,
                                15.6,
                                ScreenResolution(1920, 1080),
                                GraphicsCardModel("GTX", 1650),
                                2000,
                                52.00,
                                DesignMark.EXCELLENT
                        ),
                        Notebook(
                                4,
                                "Gigabyte G5 GE",
                                6,
                                256,
                                2,
                                80000,
                                13.6,
                                ScreenResolution(1280, 720),
                                GraphicsCardModel("RTX", 3050),
                                2990,
                                28.96,
                                DesignMark.POOR
                        )
                )
        )

        val paretoSet = paretoProcessor(dataSet)

        assertEquals(paretoSet.notebooks.size, 2)
    }
}
