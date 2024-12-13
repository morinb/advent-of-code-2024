import kotlin.math.sign

fun mapReports(input: List<String>): List<Long> =
    input.map { it.toLong() }


fun generateSubLists(numbers: List<Long>): List<List<Long>> {
    val subLists = mutableListOf<List<Long>>()

    for (i in numbers.indices) {
        // Exclude the current element at index `i` and add the rest to the sublist
        val subList = numbers.filterIndexed { index, _ -> index != i }
        subLists.add(subList)
    }

    return subLists
}

fun main() {

    fun isValid(it: List<Long>): Boolean {
        val firstSign = it.first().sign
        return !it.contains(0) && !it.any { value -> value > 3 || value < -3 } && it.all { value -> value.sign == firstSign }
    }

    fun filterPart1(also: List<List<Long>>) = also
        .filter { isValid(it) }.also { println(it) }.size

    fun part1(input: List<String>): Int {
        return filterPart1(input.map { reports ->
            mapReports(reports.split(" "))
                .zipWithNext { a, b -> b - a }
        }.also { println(it) })
    }

    fun part2(input: List<String>): Int {

        val (okReports, maybeOkReports) = input.map { reports ->
            mapReports(reports.split(" "))
        }.partition { isValid(it.zipWithNext { a, b -> b - a }) }
        okReports.println()
        maybeOkReports.println()

        val additionalOK = maybeOkReports.map { reports ->
            generateSubLists(reports)
                .filter { isValid(it.zipWithNext { a, b -> b - a }) }

        }.filterNot { it.isEmpty() }


        return okReports
            .size + additionalOK.size
    }


    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)


    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()


}
