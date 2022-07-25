package com.finalproj.safely.user


import com.finalproj.safely.doctor.DrAvailabilityInfo
import com.finalproj.safely.hospital.HospitalInfo
import com.finalproj.safely.patient.*
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
    fun getDrsByHospitalId(@Query("id") hospitalId: String?): Call<List<DoctorResponse>>

    @Headers("Content-Type: application/json")
    @POST("/api/patient/records/")
    fun submitRecords(@Query("id") patientId: String?, @Body medicalRecords: MedicalRecords): Call<SuccessMessageResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/patient")
    fun addPatient(@Body patientInfo: PatientInfo): Call<PatientResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/patient/location")
    fun addPatientLocation(@Query("id") patientId: String?,@Body location: Location): Call<SuccessMessageResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/hospital")
    fun addHospitalInfo(@Body hospitalInfo: HospitalInfo): Call<SuccessMessageResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/hospital")
    fun getAllHospitals(): Call<List<HospitalResponse>>

    @Headers("Content-Type: application/json")
    @GET("/api/patient/nearby")
    fun findNearbyHospitals(@Query("id") patientId: String?): Call<List<HospitalResponse>>

    @Headers("Content-Type: application/json")
    @POST("/api/doctor")
    fun addDoctor(@Body doctor: DoctorInfo): Call<SuccessMessageResponse>

    @Headers("Content-Type: application/json")
    @POST("api/user/profile")
    fun editUserProfile(@Query("id") userId: String?,@Body userInfo: UserInfo): Call<SuccessMessageResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/doctor/available")
    fun addDrAvailability(@Body availability: DrAvailabilityInfo): Call<SuccessMessageResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/doctor/available")
    fun getAvailabilityByDrId(@Query("id") doctorId: String?): Call<List<DrAvailabilityResponse>>

    @Headers("Content-Type: application/json")
    @GET("/api/doctor/available/times")
    fun getTimesByAvailabilityId(@Query("id") availabilityId: String?): Call<TimesResponse>

}