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

class ChooseTimeActivity : AppCompatActivity(), TimesAdapter.OnItemClickListener {
    private lateinit var availability_id: String
    private lateinit var timesAdapter: TimesAdapter
    private lateinit var timesList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_time)
        val imageView: ImageView = findViewById(R.id.time)
        Glide.with(this).load(R.drawable.time).into(imageView)
        intent.getStringExtra("availability_id")
            ?.let {
                availability_id = it
                Log.d("availability_id", it)
            }

        val apiService = RestApiService()
        apiService.getTimesByAvailabilityId(availability_id) {
            if (it != null) {
                timesList = it.times as ArrayList<String>
            }
            Log.d("times", it.toString())
            timesAdapter.submitList(timesList)
        }
        initRecyclerView()
        clickedNavItem()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.t_recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ChooseTimeActivity)
            timesAdapter = TimesAdapter(this@ChooseTimeActivity)
            adapter = timesAdapter
        }
    }

    override fun onItemClick(position: Int) {
//        val intent = Intent(this, PatientDoctorsActivity::class.java)
        val clickedItem: String = timesList[position]
        Log.d("CLICKED", clickedItem)
//        clickedItem._id?.let {
//            intent.putExtra("availability_id", it)
//        }
        timesAdapter.notifyItemChanged(position)
//        startActivity(intent)
    }

    private fun clickedNavItem() {
        val bottomnav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomnav.menu.findItem(R.id.nav_hospitals).isChecked = true;

        bottomnav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent =
                        Intent(this@ChooseTimeActivity, PatientHomeActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_hospitals -> {

                }
                R.id.nav_location -> {
                    val intent = Intent(this@ChooseTimeActivity, MapsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_records -> {
                    val intent = Intent(this@ChooseTimeActivity,
                        AddMedicalRecordsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_profile -> {
                    val intent = Intent(this@ChooseTimeActivity,
                        PatientProfileActivity::class.java)
                    startActivity(intent)
                }
            }
            return@setOnItemSelectedListener true
        }
    }
}