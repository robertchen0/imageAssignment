package com.example.imageassignment.view

import com.example.imageassignment.model.ImageData

interface ViewContract {
    fun initUI()
    fun initPresenter()
    fun initSearch()
    fun passImageData(dataSet: ArrayList<ImageData>)
    fun getErrorMessage(errorMessage: String)
}