package com.finalproj.safely

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val btn_try_now = findViewById(R.id.trynow) as Button
        btn_try_now.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, UserTypeActivity::class.java)
            startActivity(intent)
        }
    }
}