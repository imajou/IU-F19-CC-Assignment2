class ExpressionIterator(private val data: List<String>) {
    private var current: Int = -1

    fun next(): String? {
        current += 1
        if (!hasNext()) {
            current -= 1
            return null
        }
        return data[current]
    }

    fun hasNext(): Boolean {
        return current < data.size
    }

    fun last(): String? {
        if (!hasNext()) return null
        return data[current]
    }

}