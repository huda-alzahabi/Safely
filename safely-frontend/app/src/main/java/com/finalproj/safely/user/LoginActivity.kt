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
            var token = ""

            if (it?.token != null) {
                val elements = it.token.split('.')
                if (elements.size == 3) {
                    val (header, payload, signature) = elements
                    val p = Base64.decode(payload, Base64.DEFAULT).decodeToString()
                    val json = JSONObject(p)
                    user_name = json.getString(("name").toString())
                    email = json.getString(("email").toString())
                    user_type = json.getString(("userType").toString())
                    _id = json.getString(("_id").toString())
                } else {
                    error("Invalid token")
                }
                token = it.token
                val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
                    Context.MODE_PRIVATE)

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("Token", token)
                editor.putString("Name", user_name)
                editor.putString("Email", email)
                editor.putString("user_id", _id)
                editor.putString("user_type", user_type)
                editor.apply()
                editor.commit()

                if (user_type == "patient") {
                    apiService.getPatientByUserId(_id, token) {
                        if (it != null) {
                            val patientId = it._id
                            editor.putString("patient_id", patientId)
                            editor.apply()
                            editor.commit()
                            val intent = Intent(this@LoginActivity, PatientHomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Log.d("Error", "No patient response")
                        }
                    }
                } else
                    if (user_type == "doctor") {
                        apiService.getDoctorByUserId(_id, token) {
                            if (it != null) {
                                val doctorId = it._id
                                editor.putString("doctor_id", doctorId)
                                editor.apply()
                                editor.commit()
                                val intent =
                                    Intent(this@LoginActivity, DoctorHomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Log.d("Error", "No doctor response")
                            }
                        }
                    } else
                        if (user_type == "hospital") {
                            apiService.getHospitalByUserId(_id, token) {
                                if (it != null) {
                                    val hospitalId = it._id
                                    val hospitalAddress =
                                        it.address.street + ", " + it.address.city + ", " + it.address.country
                                    val phoneNum = it.phone_number
                                    val hours = it.outpatient_clinic
                                    editor.putString("hospital_id", hospitalId)
                                    editor.putString("phone_num", phoneNum)
                                    editor.putString("hospital_address", hospitalAddress)
                                    editor.putString("hours", hours)
                                    editor.apply()
                                    editor.commit()
                                    val intent =
                                        Intent(this@LoginActivity, HospitalHomeActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Log.d("Error", "No hospital response")
                                }
                            }
                        } else {
                            error("Invalid user type")
                        }

            } else {
                Log.d("Login Error", "Error logging new user")
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        }
    }
}
