package com.example.imageassignment.view

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.imageassignment.R
import com.example.imageassignment.model.ImageData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*


class CustomAdapter(var dataSet: ArrayList<ImageData>, var listener: Listener) :
    RecyclerView.Adapter<CustomAdapter.CustomViewHolder>(){

    lateinit var sharedPreferences: SharedPreferences
    lateinit var url: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder =
        CustomViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent, false
            )
        )

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        url = dataSet[position].thumbnailUrl
        holder?.updateWithUrl(url)

        holder.ivImage.setOnClickListener ( object : View.OnClickListener {
            override fun onClick(v: View?) {
                listener.createImageDetailFragment(dataSet[position])
            }
        })
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivImage: ImageView = itemView.findViewById(R.id.iv_image)

        fun updateWithUrl(url: String) {
            Picasso.get().load(url).resize(400, 400).into(ivImage)
        }

    }
}