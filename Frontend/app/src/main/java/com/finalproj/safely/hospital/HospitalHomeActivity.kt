package com.finalproj.safely.hospital

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.finalproj.safely.R
import com.finalproj.safely.user.MainActivity

class HospitalHomeActivity : AppCompatActivity() {
    val sharedPrefFile = "kotlin_shared_preference"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_home)
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