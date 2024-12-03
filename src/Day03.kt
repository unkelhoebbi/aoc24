import java.math.BigInteger

fun extractValidOperations(input: List<String>): List<Pair<BigInteger, BigInteger>> {
    val operations = mutableListOf<String>()
    val regexMul = Regex("mul\\((\\d+),(\\d+)\\)")
    val regexDont = Regex("don't\\(\\)")
    val regexDo = Regex("do\\(\\)")

    val combinedRegex = Regex("mul\\((\\d+),(\\d+)\\)|don't\\(\\)|do\\(\\)")
    input.forEach { line ->
        combinedRegex.findAll(line).forEach { operations.add(it.value) }
    }

    val list = mutableListOf<Pair<BigInteger, BigInteger>>()
    var mulEnabled = true

    for (operation in operations) {
        when {
            regexDont.matches(operation) -> mulEnabled = false
            regexDo.matches(operation) -> mulEnabled = true
            regexMul.matches(operation) && mulEnabled -> {
                val match = regexMul.find(operation)!!
                val (first, second) = match.destructured
                list.add(Pair(first.toBigInteger(), second.toBigInteger()))
            }
        }
    }
    return list
}

fun calculateSumOfProducts(pairs: List<Pair<BigInteger, BigInteger>>): BigInteger {
    val products = pairs.map { it.first * it.second }
    return products.fold(BigInteger.ZERO, BigInteger::add)
}

fun main() {
    val extractedPairs = extractValidOperations(readInput("Day03"))
    println("Extracted pairs: $extractedPairs")
    val result = calculateSumOfProducts(extractedPairs)
    println("Sum of products: $result")
}
