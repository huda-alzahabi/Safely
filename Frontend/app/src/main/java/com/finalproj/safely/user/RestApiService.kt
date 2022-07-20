package com.finalproj.safely.user

import android.content.ContentValues.TAG
import android.util.Log
import android.util.Log.d
import com.finalproj.safely.patient.Doctor
import com.finalproj.safely.patient.MedicalRecords
import com.finalproj.safely.patient.SuccessMessageResponse
import com.finalproj.safely.patient.PatientInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {
    fun register(userData: UserInfo, onResult: (SignupResponse?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.register(userData).enqueue(
            object : Callback<SignupResponse> {
                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                    Log.d("Added 32User", t.toString())
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

    fun getDrs(onResult: (List<Doctor?>) -> Unit) {
        val retrofitInstance = ServiceBuilder.buildService(RestApi::class.java)
        retrofitInstance.getDrsByHospitalId("62c1fad00a4ae6cd447d12bf")
            .enqueue(object : Callback<List<Doctor>?> {
                override fun onResponse(
                    call: Call<List<Doctor>?>,
                    response: Response<List<Doctor>?>,
                ) {
                    val responseBody = response.body()!!
                    onResult(responseBody)
                    Log.d("DOCTORSSS", responseBody.toString())
                }

                override fun onFailure(call: Call<List<Doctor>?>, t: Throwable) {
                    d(TAG, "onFailure: " + t.message)
                }
            })
    }
    fun submitRecords(medicalRecords: MedicalRecords, onResult: (SuccessMessageResponse?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.submitRecords("62c1f77a3841c742c4986d76",medicalRecords).enqueue(
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
    fun addPatient(patientInfo: PatientInfo, onResult: (SuccessMessageResponse?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addPatient(patientInfo).enqueue(
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

}