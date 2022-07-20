package com.finalproj.safely.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.finalproj.safely.R

class UserTypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_type)

        val patient = findViewById<Button>(R.id.patient)
        val doctor = findViewById<Button>(R.id.doctor)
        val hospital = findViewById<Button>(R.id.hospital)
        patient.setOnClickListener {
            val intent = Intent(this@UserTypeActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
        doctor.setOnClickListener {
            val intent = Intent(this@UserTypeActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
        hospital.setOnClickListener {
            val intent = Intent(this@UserTypeActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

    }
}