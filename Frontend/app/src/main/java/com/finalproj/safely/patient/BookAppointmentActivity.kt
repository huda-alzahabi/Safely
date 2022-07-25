package com.finalproj.safely.patient

import android.content.Intent
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

class BookAppointmentActivity : AppCompatActivity(), DaysAdapter.OnItemClickListener {
    private lateinit var doctor_id: String
    private lateinit var daysAdapter: DaysAdapter
    private lateinit var daysList: List<DrAvailabilityResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)
        val imageView: ImageView = findViewById(R.id.r)
        Glide.with(this).load(R.drawable.gif).into(imageView)
        intent.getStringExtra("doctor_id")
            ?.let {
                doctor_id = it
                Log.d("doctor_id", it)
            }

        val apiService = RestApiService()
        apiService.getAvailabilityByDrId(doctor_id) {
            daysList = it as List<DrAvailabilityResponse>
            Log.d("availability", it.toString())
            daysAdapter.submitList(daysList)
        }
        initRecyclerView()
        clickedNavItem()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.d_recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@BookAppointmentActivity)
            daysAdapter = DaysAdapter(this@BookAppointmentActivity)
            adapter = daysAdapter
        }
    }


    private fun clickedNavItem() {
        val bottomnav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomnav.menu.findItem(R.id.nav_hospitals).isChecked = true;

        bottomnav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent =
                        Intent(this@BookAppointmentActivity, PatientHomeActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_hospitals -> {

                }
                R.id.nav_location -> {
                    val intent = Intent(this@BookAppointmentActivity, MapsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_records -> {
                    val intent = Intent(this@BookAppointmentActivity,
                        AddMedicalRecordsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_profile -> {
                    val intent = Intent(this@BookAppointmentActivity,
                        PatientProfileActivity::class.java)
                    startActivity(intent)
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, PatientDoctorsActivity::class.java)
        val clickedItem: DrAvailabilityResponse = daysList[position]
        Log.d("hospitals", clickedItem._id)
        clickedItem._id?.let {
            intent.putExtra("hospital_id", it)
        }
        daysAdapter.notifyItemChanged(position)
        startActivity(intent)
    }
}