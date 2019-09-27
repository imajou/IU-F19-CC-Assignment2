package elements

open class Relation(first: Expression, second: Expression?) : Binary(first, second) {
    override fun calculate(): Long {
        return first.calculate()
    }

    companion object {
        const val LESS: String = "<"
        const val GREATER: String = ">"
        const val EQUAL: String = "="
    }

    class Less(left: Expression, right: Expression) : Relation(left, right) {
        override fun calculate(): Long {
            return if (first.calculate() < (second?.calculate() as Long)) 1 else 0
        }
    }

    class Greater(left: Expression, right: Expression) : Relation(left, right) {
        override fun calculate(): Long {
            return if (first.calculate() > (second?.calculate() as Long)) 1 else 0
        }
    }

    class Equal(left: Expression, right: Expression) : Relation(left, right) {
        override fun calculate(): Long {
            return if (first.calculate() == (second?.calculate() as Long)) 1 else 0
        }
    }

}