package com.finalproj.safely.user

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.finalproj.safely.R
import com.finalproj.safely.doctor.AllHospitalsActivity
import com.finalproj.safely.hospital.HospitalHomeActivity
import com.finalproj.safely.patient.Hospital
import com.finalproj.safely.patient.PatientInfoActivity


class SignUpActivity : AppCompatActivity() {
    lateinit var builder: AlertDialog.Builder
    lateinit var alertDialog: AlertDialog

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
        val sharedPrefFile = "kotlin_shared_preference"
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val usertype = sharedPreferences.getString("usertype", "")

        val apiService = RestApiService()
        val userInfo = UserInfo(
            name = name,
            email = email,
            password = password,
            userType = usertype
        )

        apiService.register(userInfo) {
            Log.d("User", userInfo.toString())
            if (it != null) {
                Log.d("HEYYY", it.toString())
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("Name", name)
                editor.apply()
                editor.commit()
                if (usertype != null) {
                    Log.d("Usertype", usertype)
                }
                if (usertype == "patient") {
                    val intent = Intent(this, PatientInfoActivity::class.java)
                    startActivity(intent)
                } else if (usertype == "doctor") {
                    val intent = Intent(this, AllHospitalsActivity::class.java)
                    startActivity(intent)
                } else if (usertype == "hospital") {
                    val intent = Intent(this, HospitalHomeActivity::class.java)
                    startActivity(intent)
                }
            } else {
                Log.d("OKKKKK", "Error registering new user")

                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        }
    }
}