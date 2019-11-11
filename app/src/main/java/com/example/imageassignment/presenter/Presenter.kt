package com.example.imageassignment.presenter

import com.example.imageassignment.model.ImageData
import com.example.imageassignment.model.Network
import com.example.imageassignment.view.ViewContract

class Presenter: PresenterContract {
    var view: ViewContract? = null

    override fun initView(view: ViewContract) {
        this.view = view
    }

    override fun destroyView() {
        view = null
    }

    fun searchImage() {
        Network(this).initRetrofit()
    }

    override fun sendData(dataSet: ArrayList<ImageData>) {
        view?.passImageData(dataSet)
    }
    override fun sendError(errorMessage: String) {
        view?.getErrorMessage(errorMessage)
    }

}