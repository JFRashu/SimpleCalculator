package com.jfrashu.calculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.function.Function
import kotlin.math.PI
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var inputExpression: EditText
    private lateinit var displayResult: TextView

    // Define sqrt function properly using exp4j Function class
    private val sqrtFunction = object : Function("sqrt", 1) {
        override fun apply(vararg args: Double): Double {
            return sqrt(args[0])
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputExpression = findViewById(R.id.input_expression)
        displayResult = findViewById(R.id.display_result)

        setupNumberButtons()
        setupOperatorButtons()
        setupSpecialButtons()
        setupInputChangeListener()
    }

    private fun setupNumberButtons() {
        val numberIds = arrayOf(
            R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4,
            R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9,
            R.id.button_point
        )

        numberIds.forEach { id ->
            findViewById<MaterialButton>(id).setOnClickListener {
                appendToExpression((it as MaterialButton).text.toString())
            }
        }
    }

    private fun setupOperatorButtons() {
        val operatorIds = arrayOf(
            R.id.button_plus, R.id.button_minus, R.id.button_multiply, R.id.button_divide,
            R.id.button_percent, R.id.button_lb, R.id.button_rb
        )

        operatorIds.forEach { id ->
            findViewById<MaterialButton>(id).setOnClickListener {
                appendToExpression((it as MaterialButton).text.toString())
            }
        }
    }

    private fun setupSpecialButtons() {
        findViewById<MaterialButton>(R.id.button_pi).setOnClickListener {
            appendToExpression(PI.toString())
        }

        findViewById<MaterialButton>(R.id.button_square).setOnClickListener {
            val currentText = inputExpression.text.toString()
            if (currentText.isNotEmpty()) {
                appendToExpression("^2")
            }
        }

        findViewById<MaterialButton>(R.id.button_root).setOnClickListener {
            appendToExpression("√(")
        }

        findViewById<MaterialButton>(R.id.button_ac).setOnClickListener {
            inputExpression.setText("")
            displayResult.text = "0"
        }

        findViewById<MaterialButton>(R.id.button_clear).setOnClickListener {
            val currentText = inputExpression.text.toString()
            if (currentText.isNotEmpty()) {
                inputExpression.setText(currentText.substring(0, currentText.length - 1))
            }
        }

        findViewById<MaterialButton>(R.id.button_equal).setOnClickListener {
            calculateResult(true)
        }
    }

    private fun setupInputChangeListener() {
        inputExpression.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateResult(false)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun appendToExpression(value: String) {
        val currentText = inputExpression.text.toString()
        val newText = currentText + value
        inputExpression.setText(newText)
        inputExpression.setSelection(newText.length)
    }

    private fun calculateResult(isEqualPressed: Boolean) {
        try {
            val expression = inputExpression.text.toString()
                .replace("×", "*")
                .replace("÷", "/")
                .replace("π", PI.toString())

            if (expression.isEmpty()) {
                displayResult.text = "0"
                return
            }

            val result = evaluateExpression(expression)

            if (isEqualPressed) {
                inputExpression.setText(formatResult(result))
                inputExpression.setSelection(inputExpression.text.length)
                displayResult.text = "0"
            } else {
                displayResult.text = formatResult(result)
            }
        } catch (e: Exception) {
            if (isEqualPressed) {
                displayResult.text = "Error"
            }
        }
    }

    private fun evaluateExpression(expression: String): Double {
        return ExpressionBuilder(expression)
            .function(sqrtFunction)  // Using the properly defined sqrt function
            .build()
            .evaluate()
    }

    private fun formatResult(result: Double): String {
        return if (result % 1 == 0.0) {
            result.toLong().toString()
        } else {
            String.format("%.8f", result).trimEnd('0').trimEnd('.')
        }
    }
}