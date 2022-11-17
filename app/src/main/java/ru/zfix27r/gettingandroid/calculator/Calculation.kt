package ru.zfix27r.gettingandroid.calculator

class Calculation {
    fun operate(n1: Double, n2: Double, operation: Buttons) =
        when (operation) {
            Buttons.PLUS -> plus(n1, n2)
            Buttons.MINUS -> minus(n1, n2)
            Buttons.DIVIDE -> divide(n1, n2)
            Buttons.MULTIPLY -> multiply(n1, n2)
            else -> 0.0
        }

    private fun plus(n1: Double, n2: Double) = n1 + n2
    private fun minus(n1: Double, n2: Double) = n1 - n2
    private fun divide(n1: Double, n2: Double) = if (n2 == 0.0) n2 else n1 / n2
    private fun multiply(n1: Double, n2: Double) = n1 * n2
}