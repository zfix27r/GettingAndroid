package ru.zfix27r.gettingandroid

import ru.zfix27r.gettingandroid.calculator.Buttons
import ru.zfix27r.gettingandroid.calculator.Buttons.*
import ru.zfix27r.gettingandroid.calculator.Logic
import ru.zfix27r.gettingandroid.calculator.Operator

class Calculator : SaveInstanceState {
    private val logic = Logic()
    private val operator = Operator()

    val calculation: String
        get() = "${logic.calculationLog}${operator.operation?.symbol ?: ""}"

    val result: String
        get() = logic.result

    override fun getSaveInstanceState(): String {
        return logic.getSaveInstanceState() + operator.getSaveInstanceState()
    }

    override fun setSaveInstanceState(save: String) {
        logic.setSaveInstanceState(save)
        operator.setSaveInstanceState(save)
    }

    fun pressButton(btn: Buttons) {
        when (btn) {
            BACKSPACE -> backspace()
            C -> clear()
            DOT -> dot()
            PLUS, MINUS, DIVIDE, MULTIPLY -> operation(btn)
            EQUALLY -> equally()
            else -> digit(btn)
        }

        if (btn != BACKSPACE) operator.lastBtn = btn
    }

    private fun backspace() {
        logic.dropLast()
    }

    private fun clear() {
        operator.resetOperation()
        logic.resetCalculationLog()
        logic.resetN1()
        logic.resetN2()
    }

    private fun dot() {
        logic.addDot()
    }

    private fun operation(operation: Buttons) {
        if (operator.isLastNotOperation()) logic.calculate(operator.operation)
        operator.operation = operation
    }

    private fun digit(btn: Buttons) {
        logic.addDigit(btn.symbol)
    }

    private fun equally() {
        logic.calculate(operator.operation)
        operator.resetOperation()
        logic.resetCalculationLog()
    }
}