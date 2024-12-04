fun rotate45Degrees(pattern: Array<CharArray>): Array<CharArray> {
    val result = Array(3) { CharArray(3) { '*' } }
    result[0][0] = pattern[0][1]
    result[0][2] = pattern[1][2]
    result[2][0] = pattern[1][0]
    result[2][2] = pattern[2][1]
    result[1][1] = pattern[1][1]

    return result
}

fun rotate90Degrees(pattern: Array<CharArray>, direction: String): Array<CharArray> {
    val result = Array(3) { CharArray(3) { '*' } }
    result[1][1] = pattern[1][1]
    result[0][1] = pattern[1][2]
    result[1][0] = pattern[0][1]
    result[1][2] = pattern[2][1]
    result[2][1] = pattern[1][0]

    return result
}

fun main() {
    val pattern1 = arrayOf(
        charArrayOf('*', 'M', '*'),
        charArrayOf('M', 'A', 'S'),
        charArrayOf('*', 'S', '*')
    )

    val pattern2 = arrayOf(
        charArrayOf('*', 'S', '*'),
        charArrayOf('M', 'A', 'S'),
        charArrayOf('*', 'M', '*')
    )

    val patterns = listOf(pattern1, pattern2)

    patterns.forEach { pattern ->
        println("Original Pattern:")
        pattern.forEach { println(it.joinToString(" ")) }
        println("Rotated 45 degrees left:")
        rotate45Degrees(pattern, "left").forEach { println(it.joinToString(" ")) }
        println("Rotated 45 degrees right:")
        rotate45Degrees(pattern, "right").forEach { println(it.joinToString(" ")) }
        println()
    }
}