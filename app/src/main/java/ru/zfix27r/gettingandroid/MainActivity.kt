package ru.zfix27r.gettingandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate)

        init()
        savedInstanceState?.let { calculator.setSaveInstanceState(it.getString(SAVE_NAME)) }
        updateUI()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SAVE_NAME, calculator.getSaveInstanceState())
    }

    private fun init() {
        val key1: Button = findViewById(R.id.key_1_btn)
        val key2: Button = findViewById(R.id.key_2_btn)
        val key3: Button = findViewById(R.id.key_3_btn)
        val key4: Button = findViewById(R.id.key_4_btn)
        val key5: Button = findViewById(R.id.key_5_btn)
        val key6: Button = findViewById(R.id.key_6_btn)
        val key7: Button = findViewById(R.id.key_7_btn)
        val key8: Button = findViewById(R.id.key_8_btn)
        val key9: Button = findViewById(R.id.key_9_btn)
        val key0: Button = findViewById(R.id.key_0_btn)
        val keyDot: Button = findViewById(R.id.key_dot_btn)
        key1.setOnClickListener { onClickKey(Keys.KEY_1) }
        key2.setOnClickListener { onClickKey(Keys.KEY_2) }
        key3.setOnClickListener { onClickKey(Keys.KEY_3) }
        key4.setOnClickListener { onClickKey(Keys.KEY_4) }
        key5.setOnClickListener { onClickKey(Keys.KEY_5) }
        key6.setOnClickListener { onClickKey(Keys.KEY_6) }
        key7.setOnClickListener { onClickKey(Keys.KEY_7) }
        key8.setOnClickListener { onClickKey(Keys.KEY_8) }
        key9.setOnClickListener { onClickKey(Keys.KEY_9) }
        key0.setOnClickListener { onClickKey(Keys.KEY_0) }
        keyDot.setOnClickListener { onClickKey(Keys.KEY_DOT) }

        val keyBackspace: Button = findViewById(R.id.key_backspace_btn)
        keyBackspace.setOnClickListener { onClickKey(Keys.KEY_BACKSPACE) }

        val keyC: Button = findViewById(R.id.key_c_btn)
        keyC.setOnClickListener { onClickKey(Keys.KEY_C) }

        val keyPlus: Button = findViewById(R.id.key_plus_btn)
        val keyMinus: Button = findViewById(R.id.key_minus_btn)
        val keyDivide: Button = findViewById(R.id.key_divide_btn)
        val keyMultiply: Button = findViewById(R.id.key_multiply_btn)
        keyPlus.setOnClickListener { onClickKey(Keys.KEY_PLUS) }
        keyMinus.setOnClickListener { onClickKey(Keys.KEY_MINUS) }
        keyDivide.setOnClickListener { onClickKey(Keys.KEY_DIVIDE) }
        keyMultiply.setOnClickListener { onClickKey(Keys.KEY_MULTIPLY) }

        val keyEqually: Button = findViewById(R.id.key_equally_btn)
        keyEqually.setOnClickListener { onClickKey(Keys.KEY_EQUALLY) }
    }


    private fun onClickKey(key: Keys) {
        calculator.key(key)
        updateUI()
    }

    private fun updateUI() {
        val calculation: TextView = findViewById(R.id.calculation_tv)
        calculation.text = calculator.calculation

        val result: TextView = findViewById(R.id.result_tv)
        result.text = calculator.input
    }

    companion object {
        const val SAVE_NAME = "save"
    }
}