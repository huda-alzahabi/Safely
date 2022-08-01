package com.finalproj.safely.user

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.finalproj.safely.R
import com.finalproj.safely.doctor.AllHospitalsActivity
import com.finalproj.safely.hospital.HospitalHomeActivity
import com.finalproj.safely.hospital.HospitalInfoActivity
import com.finalproj.safely.patient.PatientInfoActivity
import org.json.JSONObject


class SignUpActivity : AppCompatActivity() {
    lateinit var builder: AlertDialog.Builder
    lateinit var alertDialog: AlertDialog
    lateinit var user_id: String
    lateinit var user_type: String
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val signUp = findViewById<Button>(R.id.signup)
        builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.signup_failed)
        builder.setMessage(R.string.email_in_use)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Exit") { dialogInterface, which ->
        }
        alertDialog = builder.create()

        val sharedPrefFile = "kotlin_shared_preference"
        sharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        user_type = sharedPreferences.getString("user_type", "")!!
        var label = findViewById<TextView>(R.id.h_name)
        if (user_type == "hospital") {
            label.setText(applicationContext.resources.getString(R.string.h_name))
        }


        signUp.setOnClickListener {
            var name = findViewById<EditText>(R.id.signup_name).text.toString()
            var email = findViewById<EditText>(R.id.signup_email).text.toString()
            var password = findViewById<EditText>(R.id.signup_pass).text.toString()

            if (name.isEmpty()) Toast.makeText(this@SignUpActivity,
                "Name cannot be empty!",
                Toast.LENGTH_SHORT).show()
            if (email.isEmpty()) Toast.makeText(this@SignUpActivity,
                "Email cannot be empty!",
                Toast.LENGTH_SHORT).show()
            if (password.isEmpty()) Toast.makeText(this@SignUpActivity,
                "Password cannot be empty!",
                Toast.LENGTH_SHORT).show()
            else registerUser(name, email, password)
        }
    }

    private fun registerUser(name: String, email: String, password: String) {


        val apiService = RestApiService()
        val userInfo = UserInfo(
            name = name,
            email = email,
            password = password,
            userType = user_type
        )

        apiService.register(userInfo) {
            Log.d("User", userInfo.toString())
            if (it != null) {
                Log.d("it", it.toString())
                user_id = it.user
                val loginInfo = LoginInfo(
                    email = email,
                    password = password
                )
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

                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("Token", it.token)
                        editor.putString("Name", user_name)
                        editor.putString("Email", email)
                        editor.putString("user_id", _id)
                        editor.putString("user_type", user_type)
                        editor.apply()
                        editor.commit()
                        intent =
                            Intent(this, if (user_type == "patient") PatientInfoActivity::class.java
                            else if (user_type == "doctor") AllHospitalsActivity::class.java else HospitalInfoActivity::class.java)

                        startActivity(intent)
                    }
                }


            } else {

                Log.d("OKKKKK", "Error registering new user")

                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        }
    }
}