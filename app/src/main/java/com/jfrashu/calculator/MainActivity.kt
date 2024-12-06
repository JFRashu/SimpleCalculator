package com.jfrashu.calculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import kotlin.math.PI

class MainActivity : AppCompatActivity() {

    private lateinit var inputExpression: EditText
    private lateinit var displayResult: TextView
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputExpression = findViewById(R.id.input_expression)
        displayResult = findViewById(R.id.display_result)

        setupObservers()
        setupNumberButtons()
        setupOperatorButtons()
        setupSpecialButtons()
        setupInputChangeListener()
    }

    private fun setupObservers() {
        viewModel.expression.observe(this) { expression ->
            if (inputExpression.text.toString() != expression) {
                inputExpression.setText(expression)
                inputExpression.setSelection(expression.length)
            }
        }

        viewModel.result.observe(this) { result ->
            displayResult.text = result
        }
    }

    private fun setupNumberButtons() {
        val numberIds = arrayOf(
            R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4,
            R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9,
            R.id.button_point
        )

        numberIds.forEach { id ->
            findViewById<MaterialButton>(id).setOnClickListener {
                viewModel.appendToExpression((it as MaterialButton).text.toString())
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
                viewModel.appendToExpression((it as MaterialButton).text.toString())
            }
        }
    }

    private fun setupSpecialButtons() {
        findViewById<MaterialButton>(R.id.button_pi).setOnClickListener {
            viewModel.appendToExpression(PI.toString())
        }

        findViewById<MaterialButton>(R.id.button_square).setOnClickListener {
            if (viewModel.expression.value?.isNotEmpty() == true) {
                viewModel.appendToExpression("^2")
            }
        }

        findViewById<MaterialButton>(R.id.button_root).setOnClickListener {
            viewModel.appendToExpression("√(")
        }

        findViewById<MaterialButton>(R.id.button_ac).setOnClickListener {
            viewModel.clearAll()
        }

        findViewById<MaterialButton>(R.id.button_clear).setOnClickListener {
            viewModel.clearLast()
        }

        findViewById<MaterialButton>(R.id.button_equal).setOnClickListener {
            viewModel.calculateResult(true)
        }
    }

    private fun setupInputChangeListener() {
        inputExpression.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newText = s?.toString() ?: ""
                if (viewModel.expression.value != newText) {
                    viewModel.appendToExpression(newText)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}