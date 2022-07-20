package com.finalproj.safely.user


import com.finalproj.safely.patient.Doctor
import com.finalproj.safely.patient.MedicalRecords
import com.finalproj.safely.patient.MedicalRecordsResponse
import retrofit2.Call
import retrofit2.http.*

interface RestApi {

    @Headers("Content-Type: application/json")
    @POST("/api/user/auth/register")
    fun register(@Body userData: UserInfo): Call<SignupResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/user/auth/login")
    fun login(@Body loginInfo: LoginInfo): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/doctor/")
    fun getDrsByHospitalId(@Query("id") hospitalId: String?): Call<List<Doctor>>

    @Headers("Content-Type: application/json")
    @POST("/api/patient/records/")
    fun submitRecords(@Query("id") patientId: String?, @Body medicalRecords: MedicalRecords): Call<MedicalRecordsResponse>

}