package com.example.imageassignment.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.imageassignment.R
import com.example.imageassignment.model.ImageData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_image_details.*
import kotlinx.android.synthetic.main.item_layout.*

class FragmentImageDetails: DialogFragment() {

    lateinit var ivImage: ImageView
    lateinit var tvTitle: TextView
    lateinit var dataSet: ImageData
    lateinit var url: String
    lateinit var button_like: Button
    lateinit var sharedPreferences: SharedPreferences
    lateinit var dialogFragment: DialogFragment

    companion object {

        private val ARG_PARAM = "myObject"
        fun newInstance(item: ImageData): FragmentImageDetails {
            val fragment = FragmentImageDetails()
            val args = Bundle()
            args.putParcelable(ARG_PARAM, item)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image_details, container, false)
        ivImage = view.findViewById(R.id.iv_image_detail)
        tvTitle = view.findViewById(R.id.tv_title_detail)
        button_like = view.findViewById(R.id.btn_like_detail)
        sharedPreferences = this.getActivity()!!.getSharedPreferences("Pref",0)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataSet = arguments?.getParcelable(ARG_PARAM)!!
        tvTitle.setText(dataSet.title)
        url = dataSet.thumbnailUrl
        Picasso.get().load(url).resize(600, 600).into(ivImage)
        sharedPreferences = this.getActivity()!!.getSharedPreferences("Pref",0)
        dialog?.setCanceledOnTouchOutside(true)

        if(sharedPreferences.getBoolean(dataSet.id.toString(),false)){
            btn_like_detail.setText("Dislike")
        }
        else{
            btn_like_detail.setText("Like")
        }

        button_like.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                if (sharedPreferences.getBoolean(dataSet.id.toString(), false)) {
                    sharedPreferences.edit().remove(dataSet.id.toString()).apply()
                    btn_like_detail.setText("Like")
                } else {
                    sharedPreferences.edit().putBoolean(dataSet.id.toString(), true).apply()
                    btn_like_detail.setText("Dislike")
                }
            }
        })
    }
}

