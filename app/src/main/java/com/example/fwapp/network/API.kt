package com.example.fwapp.network

import com.example.fwapp.model.api.UserDetail
import io.reactivex.Flowable
import retrofit2.http.GET

interface API {
    @GET("/users")
    fun grabUsers(): Flowable<List<UserDetail>>
}