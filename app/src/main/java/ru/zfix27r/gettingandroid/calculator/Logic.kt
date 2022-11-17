package ru.zfix27r.gettingandroid.calculator

import ru.zfix27r.gettingandroid.SaveInstanceState

class Logic : SaveInstanceState {
    private val calculation = Calculation()

    private var n1: String = ""
    private var n2: String = ""

    private var _calculationLog = ""
    val calculationLog: String
        get() = _calculationLog

    val result: String
        get() = n2.ifEmpty { n1.ifEmpty { "0" } }

    override fun getSaveInstanceState(): String {
        return "$n1|$n2|$_calculationLog|"
    }

    override fun setSaveInstanceState(save: String) {
        val split = save.split("|")
        if (split[0].isNotEmpty()) n1 = split[0]
        if (split[1].isNotEmpty()) n2 = split[1]
        if (split[2].isNotEmpty()) _calculationLog = split[2]
    }

    fun addDigit(digit: String) {
        if (n2.length == MAX_INPUT_DIGITS) return

        n2 += digit
    }

    fun addDot() {
        if (result.length == MAX_INPUT_DIGITS) return
        if (result.contains(Buttons.DOT.symbol)) return

        n2 = "${result}${Buttons.DOT.symbol}"
    }

    fun dropLast() {
        if (n2.isEmpty()) return
        n2 = n2.substring(0, n2.length - 1)
    }

    fun resetN1() {
        n1 = "0"
    }

    fun resetN2() {
        n2 = ""
    }

    fun resetCalculationLog() {
        _calculationLog = ""
    }

    fun calculate(operation: Buttons?) {
        clearUnusedDot()

        if (operation == null) {
            _calculationLog = result
            n1 = result
        }

        if (n2.isEmpty()) return
        if (operation != null) {
            _calculationLog += operation.symbol + n2
            n1 = calculation.operate(n1.toDouble(), n2.toDouble(), operation).toString()
            clearZeroDouble()
        }

        resetN2()
    }

    private fun clearUnusedDot() {
        if (n2.isEmpty()) return

        val size = n2.length - 1
        if (n2[size].toString() == Buttons.DOT.symbol)
            n2 = n2.substring(0, size)
    }

    private fun clearZeroDouble() {
        val match = n1.split(".")
        if (match[1] == "0") n1 = match[0]
    }

    companion object {
        const val MAX_INPUT_DIGITS = 16
    }
}