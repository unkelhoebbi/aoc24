import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    fun readInputDay05(name: String): Pair<List<String>, List<String>> {
        val lines = Path("src/$name.txt").readText().trim().lines()
        val rules = mutableListOf<String>()
        val updates = mutableListOf<String>()
        var currentArray = rules

        for (line in lines) {
            if (line.isEmpty()) {
                currentArray = updates
            } else {
                currentArray.add(line)
            }
        }

        return Pair(rules, updates)
    }

    fun isBefore(updateInstructions: List<Int>, a: Int, b: Int): Boolean {
        val indexA = updateInstructions.indexOf(a)
        val indexB = updateInstructions.indexOf(b)

        return indexA == -1 || indexB == -1 || indexA < indexB
    }

    fun parseUpdateInstruction(updateInstruction: String): List<Int> {
        return updateInstruction.split(",").map { it.trim().toInt() }
    }

    fun parseRule(rule: String): Pair<Int, Int> {
        val elements = rule.split("|")
        val a = elements[0].trim().toInt()
        val b = elements[1].trim().toInt()

        return Pair(a, b)
    }

    fun addUpMiddlePageNumbers(input: Pair<List<String>, List<String>>): Int {
        val invalidInstructions = mutableListOf<String>()

        input.second.forEach() { updateInstruction ->
            val elements = parseUpdateInstruction(updateInstruction)
            input.first.forEach() { rule ->
                val (a, b) = parseRule(rule)
                if (!isBefore(elements, a, b)) {
                    invalidInstructions.add(updateInstruction)
                }
            }
        }

        val validInstructions = input.second.filterNot { it in invalidInstructions }
        val middleElements = validInstructions.map {
            val elements = parseUpdateInstruction(it)
            elements[elements.size / 2]
        }

        return middleElements.sum()
    }


    println(addUpMiddlePageNumbers(readInputDay05("Day05")))
}