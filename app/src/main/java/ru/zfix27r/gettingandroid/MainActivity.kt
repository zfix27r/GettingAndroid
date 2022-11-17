package ru.zfix27r.gettingandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import ru.zfix27r.gettingandroid.calculator.Buttons.*

class MainActivity : AppCompatActivity() {
    private lateinit var calculation: TextView
    private lateinit var result: TextView

    private val calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        calculation = findViewById(R.id.calculation_tv)
        result = findViewById(R.id.result_tv)

        findViewById<Button>(R.id.c_btn).setOnClickListener { onPress(C) }
        findViewById<Button>(R.id.backspace_btn).setOnClickListener { onPress(BACKSPACE) }
        findViewById<Button>(R.id.one_btn).setOnClickListener { onPress(ONE) }
        findViewById<Button>(R.id.two_btn).setOnClickListener { onPress(TWO) }
        findViewById<Button>(R.id.three_btn).setOnClickListener { onPress(THREE) }
        findViewById<Button>(R.id.four_btn).setOnClickListener { onPress(FOUR) }
        findViewById<Button>(R.id.five_btn).setOnClickListener { onPress(FIVE) }
        findViewById<Button>(R.id.six_btn).setOnClickListener { onPress(SIX) }
        findViewById<Button>(R.id.seven_btn).setOnClickListener { onPress(SEVEN) }
        findViewById<Button>(R.id.eight_btn).setOnClickListener { onPress(EIGHT) }
        findViewById<Button>(R.id.nine_btn).setOnClickListener { onPress(NINE) }
        findViewById<Button>(R.id.zero_btn).setOnClickListener { onPress(ZERO) }
        findViewById<Button>(R.id.plus_btn).setOnClickListener { onPress(PLUS) }
        findViewById<Button>(R.id.minus_btn).setOnClickListener { onPress(MINUS) }
        findViewById<Button>(R.id.divide_btn).setOnClickListener { onPress(DIVIDE) }
        findViewById<Button>(R.id.multiply_btn).setOnClickListener { onPress(MULTIPLY) }
        findViewById<Button>(R.id.equally_btn).setOnClickListener { onPress(EQUALLY) }
        findViewById<Button>(R.id.dot_btn).setOnClickListener { onPress(DOT) }

        savedInstanceState?.let { bundle ->
            bundle.getString(SAVE_INSTANCE_STATE_CALCULATOR)?.let {
                calculator.setSaveInstanceState(it)
            }
        }

        updateUI()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SAVE_INSTANCE_STATE_CALCULATOR, calculator.getSaveInstanceState())
    }

    private fun onPress(button: ru.zfix27r.gettingandroid.calculator.Buttons) {
        calculator.pressButton(button)
        updateUI()
    }

    private fun updateUI() {
        calculation.text = calculator.calculation
        result.text = calculator.result
    }

    companion object {
        const val SAVE_INSTANCE_STATE_CALCULATOR = "calculator"
    }
}