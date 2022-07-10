package com.finalproj.safely

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class DoctorsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctors)
        clickedNavItem()
    }
    private fun clickedNavItem(){

        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_home -> {
                        val intent = Intent(this@DoctorsActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_hospitals -> {
                        val intent = Intent(this@DoctorsActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_location -> {
                        val intent = Intent(this@DoctorsActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_records -> {
                        val intent = Intent(this@DoctorsActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_profile -> {
                        val intent = Intent(this@DoctorsActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }
                return@setOnItemSelectedListener true
            }
    }
}
