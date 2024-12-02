import kotlin.math.absoluteValue

fun main() {
    fun isReportSafe(array: IntArray): Boolean {
        var ascending = 0
        var descending = 0
        var invalid = false
        for (i in 0 until array.size - 1) {
            when {
                (array[i + 1] - array[i]).absoluteValue < 1 || (array[i + 1] - array[i]).absoluteValue > 3 -> {
                    invalid = true
                    break
                }

                array[i] < array[i + 1] -> ascending++
                array[i] > array[i + 1] -> descending++
                else -> {
                    invalid = true
                    break
                }
            }
        }
        val isSafe = !invalid && (ascending == 0 && descending > 0 || descending == 0 && ascending > 0)
        return isSafe
    }

    fun countSafeReports(input: Array<IntArray>, doTolerate: Boolean = false): Int {
        var safeReports = 0
        input.forEach { array ->
            var isSafe = isReportSafe(array)
            if (!isSafe && doTolerate) {
                for (i in array.indices) {
                    val newArray = array.filterIndexed { index, _ -> index != i }.toIntArray()
                    val isSafeWithTolerance = isReportSafe(newArray)
                    if (isSafeWithTolerance) {
                        isSafe = true
                        break
                    }
                }
            }
            if (isSafe) {
                safeReports++
            }
        }
        return safeReports
    }

    val input = readInput("Day02")
    val intArray = input.map { row ->
        row.split(" ").map { it.toInt() }.toIntArray()
    }.toTypedArray()
    println("Task 1: Safe Reports")
    println(countSafeReports(intArray))

    println("Task 2: Safe Reports with tolerance")
    println(countSafeReports(intArray, true))
}