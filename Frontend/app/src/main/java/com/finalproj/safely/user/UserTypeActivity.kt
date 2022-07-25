package com.finalproj.safely.user

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.finalproj.safely.R

class UserTypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_type)
      val sharedPrefFile = "kotlin_shared_preference"

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)

        val patient = findViewById<Button>(R.id.patient)
        val doctor = findViewById<Button>(R.id.doctor)
        val hospital = findViewById<Button>(R.id.hospital)


        patient.setOnClickListener {

            intent = Intent(this@UserTypeActivity, SignUpActivity::class.java)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("user_type", "patient")
            editor.apply()
            editor.commit()
            startActivity(intent)
            finish()
        }
        doctor.setOnClickListener {

            intent = Intent(this@UserTypeActivity, SignUpActivity::class.java)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("user_type", "doctor")
            editor.apply()
            editor.commit()
            startActivity(intent)
            finish()
        }
        hospital.setOnClickListener {

            intent = Intent(this@UserTypeActivity, SignUpActivity::class.java)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("user_type", "hospital")
            editor.apply()
            editor.commit()
            startActivity(intent)
            finish()
        }


    }
}