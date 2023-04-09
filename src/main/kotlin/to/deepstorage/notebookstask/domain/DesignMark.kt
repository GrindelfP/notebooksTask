package to.deepstorage.notebookstask.domain

/**
 * Enum class containing desing marks for the laptops, which are "poor", "good" and "excellent".
 */
enum class DesignMark(val value: Int) {
    POOR(0),
    GOOD(1),
    EXCELLENT(2)
}
