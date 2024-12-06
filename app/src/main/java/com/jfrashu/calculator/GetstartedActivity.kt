package com.jfrashu.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GetstartedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_getstarted)
        val getStartedBtn = findViewById<Button>(R.id.getbtn)

        getStartedBtn.setOnClickListener {


            val intent = Intent(this@GetstartedActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}