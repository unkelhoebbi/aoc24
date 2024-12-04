fun findMatches(grid: Array<CharArray>): Int {
    val rows = grid.size
    val cols = grid[0].size
    var matchCount = 0

    for (row in 1 until rows - 1) {
        for (col in 1 until cols - 1) {
            if (grid[row][col] == 'A') {
                // Erstellen einer 3x3-Matrix mit A in der Mitte
                val matrix = Array(3) { CharArray(3) { '*' } }
                for (i in -1..1) {
                    for (j in -1..1) {
                        matrix[i + 1][j + 1] = grid[row + i][col + j]
                    }
                }

                // create strings vor diagonals
                val mainDiagonal = buildString {
                    for (i in 0..2) append(matrix[i][i])
                }
                val antiDiagonal = buildString {
                    for (i in 0..2) append(matrix[i][2 - i])
                }

                val isMainMatch = mainDiagonal.contains("MAS") || mainDiagonal.contains("SAM")
                val isAntiMatch = antiDiagonal.contains("MAS") || antiDiagonal.contains("SAM")

                if (isMainMatch && isAntiMatch) {
                    matchCount++
                }
            }
        }
    }

    println("Total matches found: $matchCount")
    return matchCount
}

fun readInputAsCharArray(fileName: String): Array<CharArray> {
    return readInput(fileName).map { it.toCharArray() }.toTypedArray()
}

fun main() {
    val grid = readInputAsCharArray("Day04")

    findMatches(grid)
}
