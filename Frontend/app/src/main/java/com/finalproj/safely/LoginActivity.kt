package com.finalproj.safely

import android.content.Intent
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

        val apiService = RestApiService()
        val loginInfo = LoginInfo(
            email = email,
            password = password)

        apiService.login(loginInfo) {
            if (email!=null) {
                Log.d("OKKKKK",it.toString())
            } else {

                Log.d("OKKKKK","Error loggin new user")
            }
        }
    }
    }
