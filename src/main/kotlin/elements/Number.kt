package elements

class Number(private val value: Long) : Primary() {
    override fun calculate(): Long {
        return value
    }

    override fun toString(): String {
        return "Number(${value})"
    }
}