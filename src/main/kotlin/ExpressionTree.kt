import elements.*
import elements.Number

class ExpressionTree(input: String) {

    private fun String.splitPreserve(delimiter: String): List<String> {
        return this.split(delimiter).flatMap { listOf(it, delimiter) }.dropLast(1).filterNot { it.isEmpty() }
    }

    private fun String.splitPreserve(vararg delimiters: String): List<String> {
        var result = listOf(this)
        delimiters.forEach { delimiter ->
            result = result.flatMap { it.splitPreserve(delimiter) }
        }
        return result
    }

    private class IllegalTokenException(expectedToken: String, currentToken: String?) :
        Exception("Expected \"$expectedToken\". Got \"$currentToken\"")


    val iterator: ExpressionIterator =
        ExpressionIterator(input.replace(" ", "").splitPreserve(">", "<", "=", "(", ")", "+", "-", "*"))

    /**
     * Parses full expression according the grammar.
     */
    fun getTree(): Expression {
        return parseTreeExpression()
    }

    /**
     * expression -> relation
     *
     * Parses expression as a relation.
     */
    private fun parseTreeExpression(): Expression {
        return parseTreeRelation()
    }

    /**
     * relation -> term [ ( "<" | ">" | "=" ) term ]
     *
     * Parses recursively a relation as one or two terms, separated by relation signs.
     */
    private fun parseTreeRelation(): Expression {
        val first = parseTreeTerm()
        val right: Expression

        when (iterator.last()) {
            Relation.LESS -> {
                right = parseTreeTerm()
                return Relation.Less(first, right)
            }
            Relation.GREATER -> {
                right = parseTreeTerm()
                return Relation.Greater(first, right)
            }
            Relation.EQUAL -> {
                right = parseTreeTerm()
                return Relation.Equal(first, right)
            }
        }
        return Relation(first, null)
    }

    /**
     * term -> factor { ( "+" | "-" ) factor }
     *
     * Parses recursively a term as one or more terms, separated by term signs.
     */
    private fun parseTreeTerm(): Expression {
        var first = parseTreeFactor()
        var second: Expression? = null

        var token = iterator.last()

        while (token === Term.SIGN_MINUS || token === Term.SIGN_PLUS) {
            second = parseTreeFactor()
            if (token === Term.SIGN_MINUS)
                first = Term.Subtract(first, second)
            else if (token === Term.SIGN_PLUS)
                first = Term.Add(first, second)
            token = iterator.last()
        }

        if (second == null)
            first = Term(first, null)

        return first
    }

    /**
     * factor -> primary { "*" primary }
     *
     * Parses recursively a factor as one or more terms, separated by factor signs.
     */
    private fun parseTreeFactor(): Expression {
        var first = parseTreePrimary()
        var second: Expression? = null

        var token = iterator.next()

        while (token === Factor.ASTERISK) {
            second = parseTreePrimary()
            first = Factor(first, second)
            token = iterator.next()
        }

        if (second == null)
            first = Factor(first, null)

        return first

    }

    /**
     * primary -> integer | "(" expression ")"
     * integer -> Any integer number (literal constant)
     *
     * Parses primary as parenthesized expression or simple integer.
     */
    private fun parseTreePrimary(): Expression {
        var token: String? = iterator.next()

        val primary: Expression
        val expression: Expression

        when {
            token?.toIntOrNull() != null -> primary = Number(token.toLong())
            token === Primary.PAR_OPEN -> {
                expression = getTree()
                token = iterator.last()
                if (token !== Primary.PAR_CLOSE) {
                    throw IllegalTokenException(Primary.PAR_CLOSE, token)
                }
                primary = Parenthesized(expression)
            }
            else -> throw IllegalTokenException("Int or ()", token)
        }
        return primary
    }


}