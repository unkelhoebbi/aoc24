import java.io.File
import kotlin.math.abs

fun main() {
    fun readInput(fileName: String): List<Pair<Int, Int>> {
        return File("src/$fileName.txt").readLines().map {
            val (a, b) = it.split("\\s+".toRegex()).map(String::toInt)
            a to b
        }
    }

    fun bubbleSort(list: MutableList<Int>) {
        val n = list.size
        for (i in 0 until n - 1) {
            for (j in 0 until n - i - 1) {
                if (list[j] > list[j + 1]) {
                    val temp = list[j]
                    list[j] = list[j + 1]
                    list[j + 1] = temp
                }
            }
        }
    }

    fun calculateDifferencesSum(input: List<Pair<Int, Int>>): Int {
        val list1 = input.map { it.first }.toMutableList()
        val list2 = input.map { it.second }.toMutableList()

        bubbleSort(list1)
        bubbleSort(list2)

        return list1.indices.sumOf { abs(list1[it] - list2[it]) }
    }

    val input = readInput("Day01")
    println(calculateDifferencesSum(input))
}
