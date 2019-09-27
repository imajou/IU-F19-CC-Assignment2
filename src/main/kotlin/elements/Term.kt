package elements

open class Term(first: Expression, second: Expression?) : Binary(first, second) {
    override fun calculate(): Long {
        return first.calculate()
    }

    companion object {
        const val SIGN_PLUS: String = "+"
        const val SIGN_MINUS: String = "-"
    }

    class Add(left: Expression, right: Expression) : Term(left, right) {
        override fun calculate(): Long {
            return first.calculate() + (second?.calculate() as Long)
        }

    }

    class Subtract(left: Expression, right: Expression) : Term(left, right) {
        override fun calculate(): Long {
            return first.calculate() - (second?.calculate() as Long)
        }

    }
}