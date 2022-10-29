package ru.zfix27r.gettingandroid

import ru.zfix27r.gettingandroid.Keys.*

class Calculator {
    private var _calculation: String = STRING_EMPTY
    private var _operation: Keys = KEY_PLUS
    private var _storeNumber: String = STRING_ZERO
    private var _inputNumber: String? = null
    private var _lastKey: Keys? = null

    val calculation
        get() = _calculation

    val input: String
        get() = _inputNumber ?: _storeNumber

    fun getSaveInstanceState(): String {
        return "$_calculation|$_operation|$_storeNumber|$_inputNumber|$_lastKey"
    }

    fun setSaveInstanceState(save: String?) {
        save?.let {
            val split = save.split("|")
            _calculation = split[0]
            _operation = Keys.valueOf(split[1])
            _storeNumber = split[2]
            _inputNumber = split[3]
            _lastKey = Keys.valueOf(split[4])
        }
    }

    fun key(key: Keys) {
        when (key) {
            KEY_BACKSPACE -> backspace()
            KEY_C -> clear()
            KEY_DOT -> setDot(key)
            KEY_PLUS, KEY_MINUS, KEY_DIVIDE, KEY_MULTIPLY -> {
                if (changedOperation(key)) return
                continueWithStoreNumber()
                updateCalculation(key)
                calculate()
                _operation = key
                resetInputNumber()
            }
            KEY_EQUALLY -> {
                calculate()
                resetInputNumber()
                resetOperation()
                resetCalculation()
            }
            else -> {
                if (_lastKey == KEY_EQUALLY) resetStoreNumber()
                if (_inputNumber == null || _inputNumber == KEY_0.symbol) _inputNumber = key.symbol
                else _inputNumber += key.symbol
            }
        }

        _lastKey = key
    }

    private fun backspace() {
        _inputNumber?.let {
            _inputNumber = if (it.length == 1) STRING_ZERO
            else it.substring(0, it.length - 1)
        }
    }

    private fun clear() {
        resetCalculation()
        resetOperation()
        resetStoreNumber()
        resetInputNumber()
    }

    private fun resetCalculation() {
        _calculation = STRING_EMPTY
    }

    private fun resetOperation() {
        _operation = KEY_PLUS
    }

    private fun resetStoreNumber() {
        _storeNumber = STRING_ZERO
    }

    private fun resetInputNumber() {
        _inputNumber = null
    }

    private fun setDot(key: Keys) {
        _inputNumber?.let {
            if (it.contains(KEY_DOT.symbol)) return
            _inputNumber = "${it}${key.symbol}"
        }
    }

    private fun changedOperation(key: Keys): Boolean {
        return when (_lastKey) {
            KEY_PLUS, KEY_MINUS, KEY_DIVIDE, KEY_MULTIPLY -> {
                _calculation = _calculation.substring(0, _calculation.length - 1) + key.symbol
                _operation = key

                true
            }
            else -> false
        }
    }

    private fun continueWithStoreNumber() {
        if (_lastKey == KEY_EQUALLY) {
            _inputNumber = _storeNumber
            resetStoreNumber()
        }
    }

    private fun calculate() {
        _inputNumber?.let {
            val number1 = _storeNumber.toDouble()
            val number2 = it.toDouble()

            val result: String = when (_operation) {
                KEY_PLUS -> number1 + number2
                KEY_MINUS -> number1 - number2
                KEY_DIVIDE -> if (number2 == DOUBLE_ZERO) number2 else number1 / number2
                KEY_MULTIPLY -> number1 * number2
                else -> DOUBLE_ZERO
            }.toString()

            val match = result.split(".")
            _storeNumber = if (match[1] == STRING_ZERO) match[0] else result
        }
    }

    private fun updateCalculation(key: Keys) {
        clearUnusedDot()
        _calculation += " $input ${key.symbol}"
    }

    private fun clearUnusedDot() {
        _inputNumber?.let {
            if (it[it.length - 1].toString() == KEY_DOT.symbol)
                _inputNumber = it.substring(0, it.length - 1)
        }
    }

    companion object {
        const val STRING_ZERO = "0"
        const val STRING_EMPTY = ""
        const val DOUBLE_ZERO = 0.0
    }
}