package com.finalproj.safely.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finalproj.safely.R
import com.finalproj.safely.patient.HospitalResponse
import com.finalproj.safely.patient.NearbyHospitalsAdapter
import com.finalproj.safely.patient.TopSpacingItemDecoration
import com.finalproj.safely.user.RestApiService

class AllHospitalsActivity : AppCompatActivity(), AllHospitalsAdapter.OnItemClickListener {
    private lateinit var hospitalsAdapter: AllHospitalsAdapter
    private lateinit var hospitalsList: List<HospitalResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_hospitals)
        val apiService = RestApiService()
        apiService.getHospitals() {
            hospitalsList = it as List<HospitalResponse>
            Log.d("hospitals", it.toString())
            hospitalsAdapter.submitList(hospitalsList)

        }

        searchHospitals()
        initRecyclerView()
    }

    private fun initRecyclerView() {

        val recyclerView = findViewById<RecyclerView>(R.id.hospital_recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AllHospitalsActivity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            hospitalsAdapter = AllHospitalsAdapter(this@AllHospitalsActivity)
            adapter = hospitalsAdapter
        }
    }
    private fun searchHospitals() {
        val searchView = findViewById<SearchView>(R.id.searchAll)
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

    override fun onItemClick(position: Int) {
        val intent = Intent(this, DoctorProfileActivity::class.java)
        val clickedItem: HospitalResponse = hospitalsList[position]
        clickedItem._id?.let {
            intent.putExtra("hospital_id", it)
        }
        hospitalsAdapter.notifyItemChanged(position)
        startActivity(intent)
    }
}