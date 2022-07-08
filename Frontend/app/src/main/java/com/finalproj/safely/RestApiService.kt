package com.finalproj.safely

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {
    fun register(userData: UserInfo, onResult: (UserInfo?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.register(userData).enqueue(
            object : Callback<UserInfo> {
                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<UserInfo>, response: Response<UserInfo>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
}