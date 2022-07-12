package com.finalproj.safely

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val login = findViewById<Button>(R.id.login_btn)
        val register = findViewById<TextView>(R.id.register)

        login.setOnClickListener {
            var email = findViewById<EditText>(R.id.login_email).text.toString()
            var password = findViewById<EditText>(R.id.login_pass).text.toString()

            if (email.isEmpty()) Toast.makeText(this@LoginActivity,"Email cannot be empty!", Toast.LENGTH_SHORT).show()
            if (password.isEmpty()) Toast.makeText(this@LoginActivity,"Password cannot be empty!",
                Toast.LENGTH_SHORT).show()

            else login(email,password)
        }
        register.setOnClickListener{
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login(email:String, password:String) {
        val sharedPrefFile = "kotlinsharedpreference"
        val apiService = RestApiService()
        val loginInfo = LoginInfo(
            email = email,
            password = password)

        apiService.login(loginInfo) {
            if (it?.token!=null) {
                Log.d("Token", it.token)
                val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
                    Context.MODE_PRIVATE)
                val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                editor.putString("Token", it.token)
                editor.apply()
                editor.commit()
                val intent = Intent(this@LoginActivity, PatientHomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.d("Login Error","Error logging new user")
            }
        }
    }
    }
