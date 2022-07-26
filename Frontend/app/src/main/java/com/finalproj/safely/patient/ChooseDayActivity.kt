package com.finalproj.safely.patient

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.finalproj.safely.R
import com.finalproj.safely.user.RestApiService
import com.google.android.material.bottomnavigation.BottomNavigationView

class ChooseDayActivity : AppCompatActivity(), DaysAdapter.OnItemClickListener {
    private lateinit var doctor_id: String
    private lateinit var doctor_name: String
    private lateinit var hospital_id: String
    private lateinit var hospital_name: String
    private lateinit var daysAdapter: DaysAdapter
    private lateinit var daysList: List<DrAvailabilityResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_day)
        val sharedPrefFile = "kotlin_shared_preference"
        var sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Token", "")!!
        intent.getStringExtra("doctor_id")
            ?.let {
                doctor_id = it
                Log.d("doctor_id", it)
            }

        val apiService = RestApiService()
        apiService.getAvailabilityByDrId(doctor_id,token) {
            daysList = it as List<DrAvailabilityResponse>
            Log.d("availability", it.toString())
            daysAdapter.submitList(daysList)
        }

        doctor_name=intent.getStringExtra("doctor_name")!!
        hospital_id=intent.getStringExtra("hospital_id")!!
        hospital_name=intent.getStringExtra("hospital_name")!!

        initRecyclerView()
        clickedNavItem()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.d_recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ChooseDayActivity)
            daysAdapter = DaysAdapter(this@ChooseDayActivity)
            adapter = daysAdapter
        }
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, ChooseTimeActivity::class.java)
        intent.putExtra("doctor_id", doctor_id)
        intent.putExtra("doctor_name", doctor_name)
        intent.putExtra("hospital_id", hospital_id)
        intent.putExtra("hospital_name", hospital_name)
        val clickedItem: DrAvailabilityResponse = daysList[position]
        Log.d("availability_id", clickedItem._id)
        clickedItem?.let {
            intent.putExtra("availability_id", it._id)
            intent.putExtra("appointment_day", it.day)
        }
        daysAdapter.notifyItemChanged(position)
        startActivity(intent)
    }

    private fun clickedNavItem() {
        val bottomnav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomnav.menu.findItem(R.id.nav_hospitals).isChecked = true;

        bottomnav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent =
                        Intent(this@ChooseDayActivity, PatientHomeActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_hospitals -> {

                }
                R.id.nav_location -> {
                    val intent = Intent(this@ChooseDayActivity, MapsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_records -> {
                    val intent = Intent(this@ChooseDayActivity,
                        AddMedicalRecordsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_profile -> {
                    val intent = Intent(this@ChooseDayActivity,
                        PatientProfileActivity::class.java)
                    startActivity(intent)
                }
            }
            return@setOnItemSelectedListener true
        }
    }

}