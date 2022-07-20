package com.finalproj.safely.patient

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finalproj.safely.*
import com.finalproj.safely.user.RestApiService
import com.google.android.material.bottomnavigation.BottomNavigationView

class PatientDoctorsActivity : AppCompatActivity() {
    private lateinit var doctorAdapter: DoctorsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_doctors)

        val apiService = RestApiService()
        apiService.getDrs() {
            Log.d("doctors", it.toString())
            doctorAdapter.submitList(it as List<Doctor>)
        }
        initRecyclerView()
        clickedNavItem()
    }

    private fun initRecyclerView() {

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@PatientDoctorsActivity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            doctorAdapter = DoctorsAdapter()
            adapter = doctorAdapter
        }
    }

    private fun clickedNavItem() {
        val bottomnav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomnav.menu.findItem(R.id.nav_hospitals).isChecked = true;

        bottomnav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent =
                        Intent(this@PatientDoctorsActivity, PatientHomeActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_hospitals -> {
                    val intent =
                        Intent(this@PatientDoctorsActivity, PatientHospitalsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_location -> {
                    val intent = Intent(this@PatientDoctorsActivity, MapsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_records -> {
                    val intent =
                        Intent(this@PatientDoctorsActivity, MedicalRecordsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_profile -> {
                    val intent =
                        Intent(this@PatientDoctorsActivity, PatientProfileActivity::class.java)
                    startActivity(intent)
                }
            }
            return@setOnItemSelectedListener true
        }
    }
}