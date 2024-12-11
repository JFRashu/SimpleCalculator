package com.jfrashu.calculator

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class GetstartedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_getstarted)

        val getStartedBtn = findViewById<Button>(R.id.getbtn)

        // Create the animation
        val scaleAnimator = ObjectAnimator.ofFloat(getStartedBtn, "scaleX", 1.2f, 0.8f, 1.2f)
        scaleAnimator.setDuration(1000) // Adjust duration as needed
        scaleAnimator.repeatCount = ObjectAnimator.INFINITE // Repeat indefinitely
        scaleAnimator.interpolator = AccelerateDecelerateInterpolator()

        // Start the animation
        scaleAnimator.start()

        // Optionally, start activity after animation finishes
        getStartedBtn.setOnClickListener {
            scaleAnimator.end() // Stop animation on click
            val intent = Intent(this@GetstartedActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}