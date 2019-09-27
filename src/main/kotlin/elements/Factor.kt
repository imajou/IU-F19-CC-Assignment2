package elements

class Factor(first: Expression, second: Expression?) : Binary(first, second) {
    override fun calculate(): Long {
        return first.calculate() * (if (second == null) 1 else second.calculate())
    }

    companion object {
        const val ASTERISK: String = "*"
    }
}