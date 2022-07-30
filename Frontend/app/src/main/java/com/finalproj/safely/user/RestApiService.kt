package com.finalproj.safely.user

import android.content.ContentValues.TAG
import android.util.Log
import android.util.Log.d
import com.finalproj.safely.doctor.AppointmentResponse
import com.finalproj.safely.doctor.DrAvailabilityInfo
import com.finalproj.safely.hospital.HospitalInfo
import com.finalproj.safely.patient.*
import com.finalproj.safely.user.Doctor

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

    fun getDrs(hospitalId: String, token: String, onResult: (List<DoctorResponse?>) -> Unit) {
        val retrofitInstance = ServiceBuilder.buildService(RestApi::class.java)
        retrofitInstance.getDrsByHospitalId(hospitalId, token)
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
        token: String,
        onResult: (SuccessMessageResponse?) -> Unit,
    ) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.submitRecords(patientId, medicalRecords, token).enqueue(
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

    fun addPatient(patientInfo: PatientInfo, token: String, onResult: (PatientResponse?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addPatient(patientInfo, token).enqueue(
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
        token: String,
        onResult: (SuccessMessageResponse?) -> Unit,
    ) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addPatientLocation(patientId, location, token).enqueue(
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

    fun addHospitalInfo(
        hospitalInfo: HospitalInfo,
        token: String,
        onResult: (SuccessMessageResponse?) -> Unit,
    ) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addHospitalInfo(hospitalInfo, token).enqueue(
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

    fun getHospitals(token: String, onResult: (List<HospitalResponse?>) -> Unit) {
        val retrofitInstance = ServiceBuilder.buildService(RestApi::class.java)
        retrofitInstance.getAllHospitals(token)
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

    fun findNearbyHospitals(
        patientId: String,
        token: String,
        onResult: (List<HospitalResponse?>) -> Unit,
    ) {
        val retrofitInstance = ServiceBuilder.buildService(RestApi::class.java)
        retrofitInstance.findNearbyHospitals(patientId, token)
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

    fun addDoctor(doctor: DoctorInfo, token: String, onResult: (SuccessMessageResponse?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addDoctor(doctor, token).enqueue(
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
        token: String,
        onResult: (SuccessMessageResponse?) -> Unit,
    ) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.editUserProfile(userId, userInfo, token).enqueue(
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
        token: String,
        onResult: (SuccessMessageResponse?) -> Unit,
    ) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addDrAvailability(availability, token).enqueue(
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
                    Log.d("Added DrAvailability", response.body().toString())
                    onResult(addedUser)
                }
            })
    }

    fun getAvailabilityByDrId(
        doctorId: String,
        token: String,
        onResult: (List<DrAvailabilityResponse>?) -> Unit,
    ) {
        val retrofitInstance = ServiceBuilder.buildService(RestApi::class.java)
        retrofitInstance.getAvailabilityByDrId(doctorId, token)
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

    fun getTimesByAvailabilityId(
        availabilityId: String,
        token: String,
        onResult: (TimesResponse?) -> Unit,
    ) {
        val retrofitInstance = ServiceBuilder.buildService(RestApi::class.java)
        retrofitInstance.getTimesByAvailabilityId(availabilityId, token).enqueue(
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

    fun bookAppointment(
        appointmentInfo: AppointmentInfo,
        token: String,
        onResult: (SuccessMessageResponse?) -> Unit,
    ) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.bookAppointment(appointmentInfo, token).enqueue(
            object : Callback<SuccessMessageResponse> {
                override fun onFailure(call: Call<SuccessMessageResponse>, t: Throwable) {
                    Log.d("No Appointment", t.toString())
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<SuccessMessageResponse>,
                    response: Response<SuccessMessageResponse>,
                ) {
                    val addedUser = response.body()
                    Log.d("Appointment Booked", response.body().toString())
                    onResult(addedUser)
                }
            })
    }

    fun getAppointmentsByDrId(
        doctorId: String,
        token: String,
        onResult: (List<AppointmentResponse?>) -> Unit,
    ) {
        val retrofitInstance = ServiceBuilder.buildService(RestApi::class.java)
        retrofitInstance.getAppointmentsByDrId(doctorId, token)
            .enqueue(object : Callback<List<AppointmentResponse>?> {
                override fun onResponse(
                    call: Call<List<AppointmentResponse>?>,
                    response: Response<List<AppointmentResponse>?>,
                ) {
                    val responseBody = response.body()!!
                    onResult(responseBody)
                    Log.d("HOSPITALSS", responseBody.toString())
                }

                override fun onFailure(call: Call<List<AppointmentResponse>?>, t: Throwable) {
                    d(TAG, "onFailure: " + t.message)
                }
            })
    }

    fun addHospitalLocation(
        hospitalId: String,
        location: Location,
        token: String,
        onResult: (SuccessMessageResponse?) -> Unit,
    ) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addHospitalLocation(hospitalId, location, token).enqueue(
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
                    Log.d("Added Loc", response.body().toString())
                    onResult(addedUser)
                }
            })
    }

    fun getPatientByUserId(userId: String, token: String, onResult: (PatientResponse?) -> Unit) {
        val retrofitInstance = ServiceBuilder.buildService(RestApi::class.java)
        retrofitInstance.getPatientByUserId(userId, token)
            .enqueue(object : Callback<PatientResponse?> {
                override fun onResponse(
                    call: Call<PatientResponse?>,
                    response: Response<PatientResponse?>,
                ) {
                    val responseBody = response.body()!!
                    onResult(responseBody)
                    Log.d("patient info", responseBody.toString())
                }

                override fun onFailure(call: Call<PatientResponse?>, t: Throwable) {
                    d(TAG, "onFailure: " + t.message)
                }
            })
    }

    fun getDoctorByUserId(userId: String, token: String, onResult: (Doctor?) -> Unit) {
        val retrofitInstance = ServiceBuilder.buildService(RestApi::class.java)
        retrofitInstance.getDoctorByUserId(userId, token)
            .enqueue(object : Callback<Doctor?> {
                override fun onResponse(
                    call: Call<Doctor?>,
                    response: Response<Doctor?>,
                ) {
                    val responseBody = response.body()!!
                    onResult(responseBody)
                    Log.d("doctor info", responseBody.toString())
                }

                override fun onFailure(call: Call<Doctor?>, t: Throwable) {
                    d(TAG, "onFailure: " + t.message)
                }
            })
    }

    fun getHospitalByUserId(userId: String, token: String, onResult: (Hospital?) -> Unit) {
        val retrofitInstance = ServiceBuilder.buildService(RestApi::class.java)
        retrofitInstance.getHospitalByUserId(userId, token)
            .enqueue(object : Callback<Hospital?> {
                override fun onResponse(
                    call: Call<Hospital?>,
                    response: Response<Hospital?>,
                ) {
                    val responseBody = response.body()!!
                    onResult(responseBody)
                    Log.d("patient info", responseBody.toString())
                }

                override fun onFailure(call: Call<Hospital?>, t: Throwable) {
                    d(TAG, "onFailure: " + t.message)
                }
            })
    }
}