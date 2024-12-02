import kotlin.math.absoluteValue

fun main() {
    fun searchForSafeReports(input: Array<IntArray>): Int {
        var safeReports = 0
        input.forEach { array ->
            var previous = array[0]
            var ascending = 0
            var descending = 0
            var invalid = false
            array.forEachIndexed { index, number ->
                if (index > 0) {
                    if ((number - previous).absoluteValue < 1 || (number - previous).absoluteValue > 3) {
                        invalid = true
                    } else {
                        if (number > previous) {
                            ascending++
                        } else if (number < previous) {
                            descending++
                        }
                    }
                    previous = number
                }
            }
            if (!invalid) {
                if (ascending == 0 && descending > 0) {
                    safeReports++
                }
                if (descending == 0 && ascending > 0) {
                    safeReports++
                }
            }
        }
        return safeReports
    }

    val input = readInput("Day02")
    val intArray = input.map { row ->
        row.split(" ").map { it.toInt() }.toIntArray()
    }.toTypedArray()
    println("Task 1: Safe Reports")
    println(searchForSafeReports(intArray))
}
