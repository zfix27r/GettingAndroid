package ru.zfix27r.gettingandroid.calculator

import ru.zfix27r.gettingandroid.SaveInstanceState

class Operator : SaveInstanceState {
    var lastBtn: Buttons? = null
    var operation: Buttons? = null

    override fun getSaveInstanceState(): String {
        return "${lastBtn ?: ""}|${operation ?: ""}"
    }

    override fun setSaveInstanceState(save: String) {
        val split = save.split("|")
        if (split[3].isNotEmpty()) lastBtn = Buttons.valueOf(split[3])
        if (split[4].isNotEmpty()) operation = Buttons.valueOf(split[4])
    }

    fun resetOperation() {
        operation = null
    }

    fun isLastNotOperation(): Boolean {
        return lastBtn != Buttons.PLUS && lastBtn != Buttons.MINUS && lastBtn != Buttons.DIVIDE && lastBtn != Buttons.MULTIPLY
    }
}