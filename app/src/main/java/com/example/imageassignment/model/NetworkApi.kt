package com.example.imageassignment.model

import retrofit2.Call
import retrofit2.http.GET

interface NetworkApi {

    @GET("photos")
    fun getImages() : Call<ArrayList<ImageData>>

}