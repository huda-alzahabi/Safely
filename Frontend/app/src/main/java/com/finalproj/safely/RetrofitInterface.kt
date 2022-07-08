package com.finalproj.safely


import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitInterface {
    @POST("/api/user/auth/register")
   suspend fun registerUser(@Body requestBody: RequestBody):Response<ResponseBody>
//     @FormUrlEncoded fun registerUser(@Field ("name") name:String,
//                                      @Field ("email") email:String,
//                                      @Field ("password") password:String):Observable<String>

    @POST("/api/user/auth/login")
    @FormUrlEncoded fun loginUser(
                      @Field ("email") email:String,
                      @Field ("password") password:String): Observable<String>
}