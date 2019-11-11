package com.example.imageassignment.model

import com.example.imageassignment.presenter.PresenterContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network(val presenter: PresenterContract) {

    fun initRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val countriesApi = retrofit.create(NetworkApi::class.java)
        countriesApi.getImages()
            .enqueue(object : Callback<ArrayList<ImageData>> {
                override fun onFailure(call: Call<ArrayList<ImageData>>, t: Throwable) {
                    presenter.sendError(t.message ?: "Error")
                }

                override fun onResponse(
                    call: Call<ArrayList<ImageData>>,
                    response: Response<ArrayList<ImageData>>
                ) {
                    presenter.sendData(response.body() ?: arrayListOf())
                }

            })
    }
}