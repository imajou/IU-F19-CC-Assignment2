import elements.Expression

fun main(args: Array<String>) {
    val input: String

    when (args.size) {
        1 -> input = args[0]
        else -> throw IllegalArgumentException("Expected 1 argument, got ${args.size}")
    }

    val parser = ExpressionTree(input)
    val expression: Expression = parser.getTree()
    val result: Long = expression.calculate()

    println(result)
}