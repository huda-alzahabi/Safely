package com.finalproj.safely.hospital

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.finalproj.safely.R
import com.finalproj.safely.user.MainActivity

class HospitalHomeActivity : AppCompatActivity() {
    val sharedPrefFile = "kotlin_shared_preference"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_home)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("Name", "")!!
        val nameText=findViewById<TextView>(R.id.hosp_name)
        val phoneText=findViewById<TextView>(R.id.hosp_num)
        val addrText=findViewById<TextView>(R.id.hosp_address)
        val hosp_hours=findViewById<TextView>(R.id.hosp_hours)
        val num = sharedPreferences.getString("phone", "")!!
        val city = sharedPreferences.getString("city", "")!!
        val street = sharedPreferences.getString("street", "")!!
        val country = sharedPreferences.getString("country", "")!!
        val address= "$street, $city, $country"
        val hours = sharedPreferences.getString("outpatient_clinic", "")!!

        nameText.setText(name)
        phoneText.setText(num)
        addrText.setText(address)
        hosp_hours.setText(hours)


        val logout = findViewById<ImageView>(R.id.logout_hosp)
        logout.setOnClickListener {
            logout()
            Log.d("logout", "logout")
        }
    }
    private fun logout() {
        this.getSharedPreferences(sharedPrefFile, 0).edit().clear().apply();
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}