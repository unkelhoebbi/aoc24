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

    fun countNumberInList(list: List<Int>, number: Int): Int {
        return list.count { it == number }
    }

    fun calculateSimilarityScore(input: List<Pair<Int, Int>>): Int {
        val list1 = input.map { it.first }.toMutableList()
        val list2 = input.map { it.second }.toMutableList()
        val results = mutableListOf<Int>()
        for (i in list1.indices) {
            results.add(countNumberInList(list2, list1[i]) * list1[i])
        }
        return results.sum()
    }

    val input = readInput("Day01")
    println("Task 1: Differences Sum")
    println(calculateDifferencesSum(input))

    println("Task 2: Similarity Score")
    println(calculateSimilarityScore(input))
}
