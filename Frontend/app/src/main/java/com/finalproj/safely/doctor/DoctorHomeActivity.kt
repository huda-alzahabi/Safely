package com.finalproj.safely.doctor

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.finalproj.safely.R
import com.finalproj.safely.user.MainActivity
import com.finalproj.safely.user.RestApiService

class DoctorHomeActivity : AppCompatActivity() {

    val sharedPrefFile = "kotlin_shared_preference"
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Doctor);
        supportActionBar?.setDisplayShowTitleEnabled(false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_home)
        var sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Token", "")!!
        val doctorId=sharedPreferences.getString("doctor_id", "")!!
        val apiService = RestApiService()
        apiService.getAppointmentsByDrId(doctorId,token) {
            Log.d("appointments", it.toString())
        }

    }
    //override oncreate options menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.doctor_navigation_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.doc_availability -> {
                val intent = Intent(this, AddAvailabilityActivity::class.java)
                startActivity(intent)
            }
            R.id.doc_profile -> {
                val intent = Intent(this, DoctorHomeActivity::class.java)
                startActivity(intent)
            }
            R.id.doc_logout -> {
                logout()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun logout() {
        this.getSharedPreferences(sharedPrefFile, 0).edit().clear().apply();
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}