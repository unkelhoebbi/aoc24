import java.math.BigInteger

fun extractValidOperations(input: List<String>): List<Pair<BigInteger, BigInteger>> {
    val list: MutableList<Pair<BigInteger, BigInteger>> = mutableListOf()
    val regex = Regex("mul\\((\\d+),(\\d+)\\)")
    for (line in input) {
        val matches = regex.findAll(line)
        for (match in matches) {
            val (first, second) = match.destructured
            list.add(Pair(first.toBigInteger(), second.toBigInteger()))
        }
    }
    println(list)
    return list
}

fun calculateSumOfProducts(pairs: List<Pair<BigInteger, BigInteger>>): BigInteger {
    val products = pairs.map { it.first * it.second }
    return products.fold(BigInteger.ZERO, BigInteger::add)
}

fun main() {
    val input = readInput("Day03")
    val result = calculateSumOfProducts(extractValidOperations(input))
    println(result)
}