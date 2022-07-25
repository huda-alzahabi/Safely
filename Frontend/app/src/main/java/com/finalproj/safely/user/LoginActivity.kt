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
import androidx.appcompat.app.AlertDialog
import com.finalproj.safely.R
import com.finalproj.safely.doctor.AllHospitalsActivity
import com.finalproj.safely.doctor.DoctorHomeActivity
import com.finalproj.safely.hospital.HospitalHomeActivity
import com.finalproj.safely.patient.PatientHomeActivity
import com.finalproj.safely.patient.PatientInfoActivity
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    lateinit var builder: AlertDialog.Builder
    lateinit var alertDialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login = findViewById<Button>(R.id.login_btn)
        val register = findViewById<TextView>(R.id.register)
        builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.login_failed)
        builder.setMessage(R.string.invalid_credentials)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Exit") { dialogInterface, which ->
        }
        alertDialog = builder.create()

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
            var user_name = ""
            var email = ""
            var user_type = ""
            var _id = ""

            if (it?.token != null) {
                Log.d("Token", it.token)
                val elements = it.token.split('.')
                if (elements.size == 3) {
                    val (header, payload, signature) = elements
                    val p = Base64.decode(payload, Base64.DEFAULT).decodeToString()
                    val json = JSONObject(p)
                    Log.d("Payload", json.toString())
                    user_name = json.getString(("name").toString())
                    email = json.getString(("email").toString())
                    user_type = json.getString(("userType").toString())
                    _id = json.getString(("_id").toString())
                } else {
                    error("Invalid token")
                }
                val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
                    Context.MODE_PRIVATE)

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("Token", it.token)
                editor.putString("Name", user_name)
                editor.putString("Email", email)
                editor.putString("user_id", _id)
                editor.apply()
                editor.commit()

                if (user_type == "patient") {
                    val intent = Intent(this@LoginActivity, PatientInfoActivity::class.java)
                    startActivity(intent)
                    finish()
                } else if (user_type == "doctor") {
                    val intent = Intent(this@LoginActivity, AllHospitalsActivity::class.java)
                    startActivity(intent)
                    finish()
                } else if (user_type == "hospital") {
                    val intent = Intent(this@LoginActivity, HospitalHomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            } else {
                Log.d("Login Error", "Error logging new user")
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        }
    }
}
