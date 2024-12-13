fun main() {

    val mulRegex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

    val removeRegex = """don't\(\).*?do\(\)""".toRegex()


    fun MatchResult.mult() = destructured.let { (first, second) -> first.toInt() * second.toInt() }

    fun part1(input: String): Int = mulRegex.findAll(input).sumOf { matchResult -> matchResult.mult() }

    fun part2(input: String): Int = part1(input.replace(removeRegex, ""))

    val testInput = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    part1(testInput).println()

    val input = readInput("Day03")
    part1(input.joinToString()).println()

    val testInput2 = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
    part2(testInput2).println()

    part2(input.joinToString()).println()


}