fun main() {
    val regex = Regex("XMAS")

    fun generateAllDiagonals(matrix: Array<Array<Char>>): List<List<Char>> {
        val rows = matrix.size
        val cols = matrix[0].size
        val diagonals = mutableListOf<List<Char>>()

        // Get all diagonals from top-left to bottom-right
        for (k in 0 until rows + cols - 1) {
            val diagonal = mutableListOf<Char>()
            for (i in 0 until rows) {
                val j = k - i
                if (j in 0 until cols) {
                    diagonal.add(matrix[i][j])
                }
            }
            if (diagonal.isNotEmpty()) diagonals.add(diagonal)
        }

        // Get all diagonals from top-right to bottom-left
        for (k in 0 until rows + cols - 1) {
            val diagonal = mutableListOf<Char>()
            for (i in 0 until rows) {
                val j = i - k + cols - 1
                if (j in 0 until cols) {
                    diagonal.add(matrix[i][j])
                }
            }
            if (diagonal.isNotEmpty()) diagonals.add(diagonal)
        }

        return diagonals
    }

    fun generateDirectionalLists(input: List<String>): List<List<String>> {
        val matrix = input.map { it.toCharArray().toTypedArray() }.toTypedArray()
        val diagonals = generateAllDiagonals(matrix)

        val vertical = MutableList(input[0].length) { StringBuilder() }
        for (i in input.indices) {
            for (j in input[i].indices) {
                vertical[j].append(input[i][j])
            }
        }

        val result = listOf(
            input,
            vertical.map { it.toString() },
            diagonals.map { it.joinToString("") }
        ).flatten()

        val mirrored = result.map { it.reversed() }

        return listOf(result, mirrored)
    }

    fun countMatches(input: List<String>): Int {
        return input.sumOf { regex.findAll(it).count() }
    }

    fun getXmasCount(input: List<String>): Int {
        val lists = generateDirectionalLists(input)
        return lists.sumOf { countMatches(it) }
    }

    println(getXmasCount(readInput("Day04")))
}
