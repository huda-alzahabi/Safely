package com.finalproj.safely.user


import com.finalproj.safely.doctor.AppointmentResponse
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
    fun getDrsByHospitalId(@Query("id") hospitalId: String?,@Header("token") token:String): Call<List<DoctorResponse>>

    @Headers("Content-Type: application/json")
    @POST("/api/patient/records/")
    fun submitRecords(@Query("id") patientId: String?, @Body medicalRecords: MedicalRecords, @Header("token") token:String): Call<SuccessMessageResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/patient")
    fun addPatient(@Body patientInfo: PatientInfo,@Header("token") token:String): Call<PatientResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/patient/location")
    fun addPatientLocation(@Query("id") patientId: String?,@Body location: Location,@Header("token") token:String): Call<SuccessMessageResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/hospital")
    fun addHospitalInfo(@Body hospitalInfo: HospitalInfo,@Header("token") token:String): Call<SuccessMessageResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/hospital")
    fun getAllHospitals(@Header("token") token:String): Call<List<HospitalResponse>>

    @Headers("Content-Type: application/json")
    @GET("/api/patient/nearby")
    fun findNearbyHospitals(@Query("id") patientId: String?,@Header("token") token:String): Call<List<HospitalResponse>>

    @Headers("Content-Type: application/json")
    @POST("/api/doctor")
    fun addDoctor(@Body doctor: DoctorInfo,@Header("token") token:String): Call<SuccessMessageResponse>

    @Headers("Content-Type: application/json")
    @POST("api/user/profile")
    fun editUserProfile(@Query("id") userId: String?,@Body userInfo: UserInfo,@Header("token") token:String): Call<SuccessMessageResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/doctor/available")
    fun addDrAvailability(@Body availability: DrAvailabilityInfo,@Header("token") token:String): Call<SuccessMessageResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/doctor/available")
    fun getAvailabilityByDrId(@Query("id") doctorId: String?,@Header("token") token:String): Call<List<DrAvailabilityResponse>>

    @Headers("Content-Type: application/json")
    @GET("/api/doctor/available/times")
    fun getTimesByAvailabilityId(@Query("id") availabilityId: String?,@Header("token") token:String): Call<TimesResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/patient/book")
    fun bookAppointment(@Body appointmentInfo: AppointmentInfo,@Header("token") token:String): Call<SuccessMessageResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/doctor/appointment")
    fun getAppointmentsByDrId(@Query("id") doctorId: String?,@Header("token") token:String): Call<List<AppointmentResponse>>

    @Headers("Content-Type: application/json")
    @POST("/api/hospital/location")
    fun addHospitalLocation(@Query("id") hospitalId: String?,@Body location: Location,@Header("token") token:String): Call<SuccessMessageResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/patient/user")
    fun getPatientByUserId(@Query("id") userId: String?,@Header("token") token:String): Call<PatientResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/doctor/user")
    fun getDoctorByUserId(@Query("id") userId: String?,@Header("token") token:String): Call<DoctorResponse>


    @Headers("Content-Type: application/json")
    @GET("/api/hospital/user")
    fun getHospitalByUserId(@Query("id") userId: String?,@Header("token") token:String): Call<HospitalResponse>
}