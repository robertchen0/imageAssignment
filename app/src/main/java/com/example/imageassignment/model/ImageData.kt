package com.example.imageassignment.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageData (var albumId: Int,
                      var id: Int,
                      var title: String,
                      var url: String,
                      var thumbnailUrl: String) : Parcelable