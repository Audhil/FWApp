package com.example.fwapp.network

import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.GET

interface API {
    @GET("/users")
    fun grabUsers(): Flowable<ResponseBody>
}