package elements

class Parenthesized(private val expression: Expression) : Primary() {
    override fun calculate(): Long {
        return expression.calculate()
    }
}