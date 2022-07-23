package com.finalproj.safely.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finalproj.safely.R
import com.finalproj.safely.patient.Doctor
import com.finalproj.safely.patient.Hospital
import com.finalproj.safely.patient.PatientDoctorsActivity
import com.finalproj.safely.patient.TopSpacingItemDecoration
import com.finalproj.safely.user.BookAppointmentActivity
import com.finalproj.safely.user.RestApiService

class AllHospitalsActivity : AppCompatActivity(), HospitalsAdapter.OnItemClickListener {
    private lateinit var hospitalsAdapter: HospitalsAdapter
    private lateinit var hospitalsList: List<Hospital>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_hospitals)
        val apiService = RestApiService()
        apiService.getHospitals() {
            hospitalsList = it as List<Hospital>
            Log.d("hospitals", it.toString())
            hospitalsAdapter.submitList(hospitalsList)

        }
        initRecyclerView()
    }

    private fun initRecyclerView() {

        val recyclerView = findViewById<RecyclerView>(R.id.hospital_recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AllHospitalsActivity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            hospitalsAdapter = HospitalsAdapter(this@AllHospitalsActivity)
            adapter = hospitalsAdapter
        }
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, DoctorProfileActivity::class.java)
        val clickedItem: Hospital = hospitalsList[position]
        clickedItem._id?.let {
            intent.putExtra("hospital_id", it)
        }
        hospitalsAdapter.notifyItemChanged(position)
        startActivity(intent)
    }
}