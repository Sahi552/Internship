package com.example.retrofitapi.retrofit

import UniversityModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {
    @GET("/search")
    suspend fun getCollage(@Query("country") country: String ) : Response<UniversityModel>
}