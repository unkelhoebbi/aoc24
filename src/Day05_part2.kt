import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
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

    fun rearrangeToValidInstructions(
        rules: List<String>,
        updates: List<String>
    ): MutableList<String> {
        val parsedRules = rules.map { parseRule(it) }
        val invalidInstructions = mutableListOf<String>()

        for (update in updates) {
            val elements = parseUpdateInstruction(update).toMutableList()
            var valid = true

            for ((a, b) in parsedRules) {
                val indexA = elements.indexOf(a)
                val indexB = elements.indexOf(b)
                if (indexA != -1 && indexB != -1 && indexA > indexB) {
                    valid = false
                    break
                }
            }

            if (!valid) {
                elements.sortWith { a, b ->
                    val isARequiredBeforeB = parsedRules.any { (x, y) -> x == a && y == b }
                    val isBRequiredBeforeA = parsedRules.any { (x, y) -> x == b && y == a }
                    when {
                        isARequiredBeforeB -> -1
                        isBRequiredBeforeA -> 1
                        else -> 0
                    }
                }
                invalidInstructions.add(elements.joinToString(","))
            }
        }

        return invalidInstructions
    }


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
        val invalid = invalidInstructions.toSet().toList()
        println(invalid)
        val validInstructions = rearrangeToValidInstructions(input.first, invalid)
        val middleElements = validInstructions.map {
            val elements = parseUpdateInstruction(it)
            elements[elements.size / 2]
        }

        return middleElements.sum()
    }


    println(addUpMiddlePageNumbers(readInputDay05("Day05")))
}