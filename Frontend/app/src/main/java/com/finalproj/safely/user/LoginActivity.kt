package com.finalproj.safely.user

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.finalproj.safely.R
import com.finalproj.safely.patient.PatientHomeActivity
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val login = findViewById<Button>(R.id.login_btn)
        val register = findViewById<TextView>(R.id.register)

        login.setOnClickListener {
            var email = findViewById<EditText>(R.id.login_email).text.toString()
            var password = findViewById<EditText>(R.id.login_pass).text.toString()

            if (email.isEmpty()) Toast.makeText(this@LoginActivity,
                "Email cannot be empty!",
                Toast.LENGTH_SHORT).show()
            if (password.isEmpty()) Toast.makeText(this@LoginActivity, "Password cannot be empty!",
                Toast.LENGTH_SHORT).show()
            else login(email, password)
        }
        register.setOnClickListener {
            val intent = Intent(this@LoginActivity, UserTypeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login(email: String, password: String) {
        val sharedPrefFile = "kotlin_shared_preference"
        val apiService = RestApiService()
        val loginInfo = LoginInfo(
            email = email,
            password = password)

        apiService.login(loginInfo) {
            if (it?.token != null) {
                Log.d("Token", it.token)
                val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
                    Context.MODE_PRIVATE)

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("Token", it.token)
                editor.putString("Name", decodeToken(it.token))
                editor.apply()
                editor.commit()

                val intent = Intent(this@LoginActivity, PatientHomeActivity::class.java)
                startActivity(intent)
            } else {
                Log.d("Login Error", "Error logging new user")
            }
        }
    }

    private fun decodeToken(token: String): String {
        val elements = token.split('.')
        var user_name = ""
        if (elements.size == 3) {
            val (header, payload, signature) = elements
            val p = Base64.decode(payload, Base64.DEFAULT).decodeToString()
            val json = JSONObject(p)
             user_name = json.getString(("name").toString())
        } else {
            error("Invalid token")
        }
        return user_name
    }
}