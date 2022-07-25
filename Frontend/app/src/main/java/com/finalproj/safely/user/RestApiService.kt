package com.finalproj.safely.user

import android.content.ContentValues.TAG
import android.util.Log
import android.util.Log.d
import com.finalproj.safely.doctor.DrAvailabilityInfo
import com.finalproj.safely.hospital.HospitalInfo
import com.finalproj.safely.patient.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {
    fun register(userData: UserInfo, onResult: (SignupResponse?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.register(userData).enqueue(
            object : Callback<SignupResponse> {
                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                    Log.d("No User", t.toString())
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<SignupResponse>,
                    response: Response<SignupResponse>,
                ) {
                    val addedUser = response.body()
                    Log.d("Added User", response.body().toString())
                    onResult(addedUser)
                }
            })
    }

    fun login(loginInfo: LoginInfo, onResult: (LoginResponse?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.login(loginInfo).enqueue(
            object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>,
                ) {
                    val addedUser = response.body()
                    onResult(addedUser)
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.e("error", it.string()) }
                    }
                    Log.d("User", response.toString())
                }
            }
        )
    }

    fun getDrs(hospitalId: String, onResult: (List<DoctorResponse?>) -> Unit) {
        val retrofitInstance = ServiceBuilder.buildService(RestApi::class.java)
        retrofitInstance.getDrsByHospitalId(hospitalId)
            .enqueue(object : Callback<List<DoctorResponse>?> {
                override fun onResponse(
                    call: Call<List<DoctorResponse>?>,
                    response: Response<List<DoctorResponse>?>,
                ) {
                    val responseBody = response.body()!!
                    onResult(responseBody)
                    Log.d("DOCTORSSS", responseBody.toString())
                }

                override fun onFailure(call: Call<List<DoctorResponse>?>, t: Throwable) {
                    d(TAG, "onFailure: " + t.message)
                }
            })
    }

    fun submitRecords(
        patientId: String,
        medicalRecords: MedicalRecords,
        onResult: (SuccessMessageResponse?) -> Unit,
    ) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.submitRecords(patientId, medicalRecords).enqueue(
            object : Callback<SuccessMessageResponse> {
                override fun onFailure(call: Call<SuccessMessageResponse>, t: Throwable) {
                    Log.d("No Records", t.toString())
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<SuccessMessageResponse>,
                    response: Response<SuccessMessageResponse>,
                ) {
                    val addedUser = response.body()
                    Log.d("Added Records", response.body().toString())
                    onResult(addedUser)
                }
            })
    }

    fun addPatient(patientInfo: PatientInfo, onResult: (PatientResponse?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addPatient(patientInfo).enqueue(
            object : Callback<PatientResponse> {
                override fun onFailure(call: Call<PatientResponse>, t: Throwable) {
                    Log.d("No Patient", t.toString())
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<PatientResponse>,
                    response: Response<PatientResponse>,
                ) {
                    val addedUser = response.body()
                    Log.d("Added Patient", response.body().toString())
                    onResult(addedUser)
                }
            })
    }

    fun addPatientLocation(
        patientId: String,
        location: Location,
        onResult: (SuccessMessageResponse?) -> Unit,
    ) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addPatientLocation(patientId, location).enqueue(
            object : Callback<SuccessMessageResponse> {
                override fun onFailure(call: Call<SuccessMessageResponse>, t: Throwable) {
                    Log.d("No Patient", t.toString())
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<SuccessMessageResponse>,
                    response: Response<SuccessMessageResponse>,
                ) {
                    val addedUser = response.body()
                    Log.d("Added Patient", response.body().toString())
                    onResult(addedUser)
                }
            })
    }

    fun addHospitalInfo(hospitalInfo: HospitalInfo, onResult: (SuccessMessageResponse?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addHospitalInfo(hospitalInfo).enqueue(
            object : Callback<SuccessMessageResponse> {
                override fun onFailure(call: Call<SuccessMessageResponse>, t: Throwable) {
                    Log.d("No Hospital", t.toString())
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<SuccessMessageResponse>,
                    response: Response<SuccessMessageResponse>,
                ) {
                    val addedUser = response.body()
                    Log.d("Added Hospital", response.body().toString())
                    onResult(addedUser)
                }
            })
    }

    fun getHospitals(onResult: (List<HospitalResponse?>) -> Unit) {
        val retrofitInstance = ServiceBuilder.buildService(RestApi::class.java)
        retrofitInstance.getAllHospitals()
            .enqueue(object : Callback<List<HospitalResponse>?> {
                override fun onResponse(
                    call: Call<List<HospitalResponse>?>,
                    response: Response<List<HospitalResponse>?>,
                ) {
                    val responseBody = response.body()!!
                    onResult(responseBody)
                    Log.d("HOSPITALSS", responseBody.toString())
                }

                override fun onFailure(call: Call<List<HospitalResponse>?>, t: Throwable) {
                    d(TAG, "onFailure: " + t.message)
                }
            })
    }

    fun findNearbyHospitals(patientId: String, onResult: (List<HospitalResponse?>) -> Unit) {
        val retrofitInstance = ServiceBuilder.buildService(RestApi::class.java)
        retrofitInstance.findNearbyHospitals(patientId)
            .enqueue(object : Callback<List<HospitalResponse>?> {
                override fun onResponse(
                    call: Call<List<HospitalResponse>?>,
                    response: Response<List<HospitalResponse>?>,
                ) {
                    val responseBody = response.body()!!
                    onResult(responseBody)
                    Log.d("Nearby HOSPITALSS", responseBody.toString())
                }

                override fun onFailure(call: Call<List<HospitalResponse>?>, t: Throwable) {
                    d(TAG, "onFailure: " + t.message)
                }
            })
    }

    fun addDoctor(doctor: DoctorInfo, onResult: (SuccessMessageResponse?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addDoctor(doctor).enqueue(
            object : Callback<SuccessMessageResponse> {
                override fun onFailure(call: Call<SuccessMessageResponse>, t: Throwable) {
                    Log.d("No Patient", t.toString())
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<SuccessMessageResponse>,
                    response: Response<SuccessMessageResponse>,
                ) {
                    val addedUser = response.body()
                    Log.d("Added Doctor", response.body().toString())
                    onResult(addedUser)
                }
            })
    }

    fun editUserProfile(
        userId: String,
        userInfo: UserInfo,
        onResult: (SuccessMessageResponse?) -> Unit,
    ) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.editUserProfile(userId, userInfo).enqueue(
            object : Callback<SuccessMessageResponse> {
                override fun onFailure(call: Call<SuccessMessageResponse>, t: Throwable) {
                    Log.d("No User", t.toString())
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<SuccessMessageResponse>,
                    response: Response<SuccessMessageResponse>,
                ) {
                    val addedUser = response.body()
                    Log.d("Edited Profile", response.body().toString())
                    onResult(addedUser)
                }
            })
    }

    fun addDrAvailability(
        availability: DrAvailabilityInfo,
        onResult: (SuccessMessageResponse?) -> Unit,
    ) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addDrAvailability(availability).enqueue(
            object : Callback<SuccessMessageResponse> {
                override fun onFailure(call: Call<SuccessMessageResponse>, t: Throwable) {
                    Log.d("No DrAvailabilityInfo", t.toString())
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<SuccessMessageResponse>,
                    response: Response<SuccessMessageResponse>,
                ) {
                    val addedUser = response.body()
                    Log.d("Added DrAvailabilityInfo", response.body().toString())
                    onResult(addedUser)
                }
            })
    }

    fun getAvailabilityByDrId(doctorId: String, onResult: (List<DrAvailabilityResponse>?) -> Unit) {
        val retrofitInstance = ServiceBuilder.buildService(RestApi::class.java)
        retrofitInstance.getAvailabilityByDrId(doctorId)
            .enqueue(object : Callback<List<DrAvailabilityResponse>> {
                override fun onResponse(
                    call: Call<List<DrAvailabilityResponse>>,
                    response: Response<List<DrAvailabilityResponse>>,
                ) {
                    val responseBody = response.body()!!
                    onResult(responseBody)
                    Log.d("DOCTOR AVAILABILITY", response.toString())
                }

                override fun onFailure(call: Call<List<DrAvailabilityResponse>>, t: Throwable) {
                    d(TAG, "onFailure: " + t.message)
                }
            })
    }

    fun getTimesByAvailabilityId(availabilityId: String, onResult: (TimesResponse?) -> Unit) {
        val retrofitInstance = ServiceBuilder.buildService(RestApi::class.java)
        retrofitInstance.getTimesByAvailabilityId(availabilityId).enqueue(
            object : Callback<TimesResponse> {
                override fun onResponse(
                    call: Call<TimesResponse>,
                    response: Response<TimesResponse>,
                ) {
                    val result = response.body()
                    Log.d("Timess", response.body().toString())
                    onResult(result)
                }

                override fun onFailure(call: Call<TimesResponse>, t: Throwable) {
                    Log.d("No Times", t.toString())
                    onResult(null)
                }
            })
    }
}