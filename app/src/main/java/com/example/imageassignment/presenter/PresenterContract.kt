package com.example.imageassignment.presenter

import com.example.imageassignment.model.ImageData
import com.example.imageassignment.view.ViewContract

interface PresenterContract {
    fun initView(view: ViewContract)
    fun destroyView()
    fun sendData(dataSet: ArrayList<ImageData>)
    fun sendError(errorMessage: String)
}