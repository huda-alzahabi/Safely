package com.finalproj.safely.doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finalproj.safely.R
import com.finalproj.safely.patient.Hospital
import com.finalproj.safely.patient.TopSpacingItemDecoration
import com.finalproj.safely.user.RestApiService

class AllHospitalsActivity : AppCompatActivity() {
    private lateinit var hospitalsAdapter: HospitalsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_hospitals)
        val apiService = RestApiService()
        apiService.getHospitals() {
            Log.d("hospitals", it.toString())
            hospitalsAdapter.submitList(it as List<Hospital>)

        }
        initRecyclerView()
    }
    private fun initRecyclerView() {

        val recyclerView = findViewById<RecyclerView>(R.id.hospital_recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AllHospitalsActivity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            hospitalsAdapter = HospitalsAdapter()
            adapter = hospitalsAdapter
        }
    }
}