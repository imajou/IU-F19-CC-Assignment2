import elements.Expression
import org.junit.Test
import kotlin.test.assertEquals


class TestSource {
    @Test
    fun `1 + (26 - 98) * 15 + 777`() {
        val input: String = "1 + (26 - 98) * 15 + 777"
        val evaluated: Long = 1 + (26 - 98) * 15 + 777
        val output: Long = -302

        assertEquals(evaluated, output)

        val parser = ExpressionTree(input)
        val expression: Expression = parser.getTree()
        val result: Long = expression.calculate()

        assertEquals(result, output)
    }

    @Test
    fun `1 + (26 - 98) * 15 + 777 {less} 124`() {
        val input: String = "1 + (26 - 98) * 15 + 777 < 124"
        val evaluatedLeft: Long = 1 + (26 - 98) * 15 + 777
        val evaluatedRight: Long = 124
        val evaluated: Long = if (evaluatedLeft < evaluatedRight) 1 else 0
        val output: Long = 1

        assertEquals(evaluated, output)

        val parser = ExpressionTree(input)
        val expression: Expression = parser.getTree()
        val result: Long = expression.calculate()

        assertEquals(result, output)
    }

}