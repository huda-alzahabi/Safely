package com.finalproj.safely

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object RetrofitClient {
    private var instance: Retrofit? = null
    fun getInstance():Retrofit{
//        if (instance==null)
//            instance=Retrofit
//                .Builder()
//                .baseUrl("http://127.0.0.1:3030")
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
//        return instance!!
        // Create Retrofit
        if (instance==null)
            instance=Retrofit.Builder()
            .baseUrl("http://94.187.9.191:3030")
            .build()
        return instance!!
    }
}