package com.finalproj.safely.patient

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finalproj.safely.R
import com.finalproj.safely.doctor.HospitalsAdapter
import com.finalproj.safely.user.RestApiService
import com.google.android.material.bottomnavigation.BottomNavigationView

class NearbyHospitalsActivity : AppCompatActivity(), HospitalsAdapter.OnItemClickListener {
    private lateinit var hospitalsAdapter: HospitalsAdapter
    private lateinit var hospitalsList: List<HospitalResponse>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPrefFile = "kotlin_shared_preference"
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val patientId = sharedPreferences.getString("patient_id", "")!!

        setContentView(R.layout.activity_nearby_hospitals)
        val apiService = RestApiService()
        apiService.findNearbyHospitals (patientId) {
            hospitalsList = it as List<HospitalResponse>
            Log.d("hospitals", it.toString())
            hospitalsAdapter.submitList(hospitalsList)

        }
        searchHospitals()
        initRecyclerView()
        clickedNavItem()
    }
    private fun initRecyclerView() {

        val recyclerView = findViewById<RecyclerView>(R.id.h_recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@NearbyHospitalsActivity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            hospitalsAdapter = HospitalsAdapter(this@NearbyHospitalsActivity)
            adapter = hospitalsAdapter
        }
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, PatientDoctorsActivity::class.java)
        val clickedItem: HospitalResponse = hospitalsList[position]
        Log.d("hospitals", clickedItem._id)
        clickedItem._id?.let {
            intent.putExtra("hospital_id", it)
        }
        hospitalsAdapter.notifyItemChanged(position)
        startActivity(intent)
    }
    private fun searchHospitals() {
        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                hospitalsAdapter.filter.filter(newText)
                return false
            }
        })
    }

    private fun clickedNavItem() {
        val bottomnav= findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomnav.menu.findItem(R.id.nav_hospitals).isChecked = true;

        bottomnav.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_home -> {
                        val intent =
                            Intent(this@NearbyHospitalsActivity, PatientHomeActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(0,0);
                    }
                    R.id.nav_hospitals -> {

                    }
                    R.id.nav_location -> {
                        val intent = Intent(this@NearbyHospitalsActivity, MapsActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(0,0);
                    }
                    R.id.nav_records -> {
                        val intent = Intent(this@NearbyHospitalsActivity,
                            AddMedicalRecordsActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(0,0);
                    }
                    R.id.nav_profile -> {
                        val intent = Intent(this@NearbyHospitalsActivity,
                            PatientProfileActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(0,0);
                    }
                }
                return@setOnItemSelectedListener true
            }
    }
}
