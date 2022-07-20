package com.finalproj.safely.patient

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import com.finalproj.safely.R
import com.finalproj.safely.user.RestApiService

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.FirebaseDatabase

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setupLocClient()

    }

    private lateinit var fusedLocClient: FusedLocationProviderClient
    // use it to request location updates and get the latest location

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap //initialise map
        getCurrentLocation()
    }

    private fun setupLocClient() {
        fusedLocClient =
            LocationServices.getFusedLocationProviderClient(this)
    }

    // prompt the user to grant/deny access
    private fun requestLocPermissions() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), //permission in the manifest
            REQUEST_LOCATION)
    }

    companion object {
        private const val REQUEST_LOCATION =
            1 //request code to identify specific permission request
        private const val TAG = "MapsActivity" // for debugging
    }

    private fun getCurrentLocation() {
        // Check if the ACCESS_FINE_LOCATION permission was granted before requesting a location
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED
        ) {

            // call requestLocPermissions() if permission isn't granted
            requestLocPermissions()
        } else {

            fusedLocClient.lastLocation.addOnCompleteListener {
                // lastLocation is a task running in the background
                val location = it.result //obtain location
                //reference to the database
                val ref: FirebaseDatabase =
                    FirebaseDatabase.getInstance("https://safely-11a5a-default-rtdb.asia-southeast1.firebasedatabase.app/")
                if (location != null) {

                    val latLng = LatLng(location.latitude, location.longitude)

                    // create a marker at the exact location
                    map.addMarker(MarkerOptions().position(latLng)
                        .title("You are currently here!"))
                    // create an object that will specify how the camera will be updated
                    val update = CameraUpdateFactory.newLatLngZoom(latLng, 16.0f)

                    map.moveCamera(update)
                    //Save the location data to the database
                    val patient_confirm_location =
                        findViewById<Button>(R.id.patient_confirm_location)
                    patient_confirm_location.setOnClickListener {
                        submitLocation(location.latitude.toString(),location.latitude.toString());
                        val intent =
                            Intent(this@MapsActivity, PatientHomeActivity::class.java)
                        startActivity(intent)
                    }

                } else {
                    // if location is null , log an error message
                    Log.e(TAG, "No location found")
                }

            }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //check if the request code matches the REQUEST_LOCATION
        if (requestCode == REQUEST_LOCATION) {
            //check if grantResults contains PERMISSION_GRANTED.If it does, call getCurrentLocation()
            if (grantResults.size == 1 && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {
                getCurrentLocation()
            } else {
                //if it doesn`t log an error message
                Log.e(TAG, "Location permission has been denied")
            }
        }
    }
    private fun submitLocation(
        longitude:String,
        latitude:String
    ){
        val apiService = RestApiService()
        val patientLocation=PatientLocation(
            longitude=longitude,
            latitude=latitude
        )
        apiService.addPatientLocation(patientLocation){
            Log.d("LOCATION", patientLocation.toString())
            if (it!= null) {
                Log.d("Location", it.toString())
            } else {
                Log.d("NOO", "Error adding location")
            }
        }

    }

}