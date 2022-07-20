package com.finalproj.safely.user

import android.content.Intent
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import androidx.appcompat.app.AppCompatActivity
import com.finalproj.safely.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
        GlobalScope.launch {
            delay(4000L)
            val intent = Intent(this@MainActivity, BookAppointmentActivity::class.java)
            startActivity(intent)
            finish()
        }
} }

