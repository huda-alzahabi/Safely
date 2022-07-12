package com.finalproj.safely

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {
    fun register(userData: UserInfo, onResult: (SignupResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.register(userData).enqueue(
            object : Callback<SignupResponse> {
                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                    Log.d("Added 32User",t.toString())
                    onResult(null)
                }
                override fun onResponse( call: Call<SignupResponse>, response: Response<SignupResponse>) {
                    val addedUser = response.body()
                    Log.d("Added User",response.body().toString())
                    onResult(addedUser)


                }
           })    }
    fun login(loginInfo: LoginInfo, onResult: (LoginResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.login(loginInfo).enqueue(
            object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.e("error", it.string()) }
                    }
                    Log.d("User",response.toString())
                }
            }
        )
    }
}