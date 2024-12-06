package com.jfrashu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.function.Function
import kotlin.math.PI
import kotlin.math.sqrt

class CalculatorViewModel : ViewModel() {

    private val _expression = MutableLiveData<String>("")
    val expression: LiveData<String> = _expression

    private val _result = MutableLiveData<String>("0")
    val result: LiveData<String> = _result

    private val sqrtFunction = object : Function("sqrt", 1) {
        override fun apply(vararg args: Double): Double {
            return sqrt(args[0])
        }
    }

    fun appendToExpression(value: String) {
        val currentText = _expression.value ?: ""
        _expression.value = currentText + value
        calculateResult(false)
    }

    fun clearAll() {
        _expression.value = ""
        _result.value = "0"
    }

    fun clearLast() {
        val currentText = _expression.value ?: ""
        if (currentText.isNotEmpty()) {
            _expression.value = currentText.substring(0, currentText.length - 1)
            calculateResult(false)
        }
    }

    fun calculateResult(isEqualPressed: Boolean) {
        try {
            val expressionText = _expression.value ?: ""
            val sanitizedExpression = expressionText
                .replace("×", "*")
                .replace("÷", "/")
                .replace("π", PI.toString())

            if (sanitizedExpression.isEmpty()) {
                _result.value = "0"
                return
            }

            val calculatedResult = evaluateExpression(sanitizedExpression)
            val formattedResult = formatResult(calculatedResult)

            if (isEqualPressed) {
                _expression.value = formattedResult
                _result.value = "0"
            } else {
                _result.value = formattedResult
            }
        } catch (e: Exception) {
            if (isEqualPressed) {
                _result.value = "Error"
            }
        }
    }

    private fun evaluateExpression(expression: String): Double {
        return ExpressionBuilder(expression)
            .function(sqrtFunction)
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
