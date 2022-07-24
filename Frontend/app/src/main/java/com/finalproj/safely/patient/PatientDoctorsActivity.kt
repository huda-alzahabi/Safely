package com.finalproj.safely.patient

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finalproj.safely.*
import com.finalproj.safely.user.BookAppointmentActivity
import com.finalproj.safely.user.RestApiService
import com.google.android.material.bottomnavigation.BottomNavigationView

class PatientDoctorsActivity : AppCompatActivity(), DoctorsAdapter.OnItemClickListener {
    private lateinit var doctorAdapter: DoctorsAdapter
    private lateinit var doctorsList: List<DoctorResponse>
    private lateinit var hospital_id: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_doctors)


        intent.getStringExtra("hospital_id")
            ?.let {
                hospital_id = it
                Log.d("user_id", it)
            }

        val apiService = RestApiService()
        apiService.getDrs(hospital_id) {
            Log.d("doctors", it.toString())
            doctorsList = it as List<DoctorResponse>
            doctorAdapter.submitList(doctorsList)
        }
        searchDrs()
        initRecyclerView()
        clickedNavItem()
    }

    private fun initRecyclerView() {

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@PatientDoctorsActivity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            doctorAdapter = DoctorsAdapter(this@PatientDoctorsActivity)
            adapter = doctorAdapter
        }
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, BookAppointmentActivity::class.java)
        val clickedItem: DoctorResponse = doctorsList[position]
        doctorAdapter.notifyItemChanged(position)
        startActivity(intent)

    }

    private fun searchDrs() {
        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                doctorAdapter.filter.filter(newText)
                return false
            }
        })
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
                            Intent(this@PatientDoctorsActivity, NearbyHospitalsActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_location -> {
                        val intent = Intent(this@PatientDoctorsActivity, MapsActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.nav_records -> {
                        val intent =
                            Intent(this@PatientDoctorsActivity,
                                AddMedicalRecordsActivity::class.java)
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