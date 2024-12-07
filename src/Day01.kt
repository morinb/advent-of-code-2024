import kotlin.math.abs

/**
 * Parses a list of space-separated string pairs into a pair of lists of longs.
 *
 * @param input A list of strings, where each string contains two space-separated numeric values.
 * @return A pair of lists, where the first list contains the first numbers from the string pairs and the second list contains the second numbers.
 * @throws IllegalArgumentException If a string in the input list does not contain exactly two space-separated values.
 */
fun listPairs(input: List<String>): Pair<List<Long>, List<Long>> =
    input.map { line ->
        line.split("""\s+""".toRegex()).let {
            require(it.size == 2)
            it[0].toLong() to it[1].toLong()
        }
    }.unzip()

fun main() {
    fun part1(input: List<String>): Long {
        return listPairs(input).let { (first, second) ->
            first.sorted().zip(second.sorted()).map { (first, second) -> abs(first - second) }
        }.sum()
    }

    fun part2(input: List<String>): Long {
        return listPairs(input).let { (first, second) ->
            first.sumOf { num -> num * second.count { it == num } }
        }
    }



    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
