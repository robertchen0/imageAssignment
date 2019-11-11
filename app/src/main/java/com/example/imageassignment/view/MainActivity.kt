package com.example.imageassignment.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imageassignment.R
import com.example.imageassignment.model.ImageData
import com.example.imageassignment.presenter.Presenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewContract , Listener{

    lateinit var button_fav: Button
    lateinit var sharedPreferences: SharedPreferences

    override fun createImageDetailFragment(item: ImageData) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, FragmentImageDetails.newInstance(item)).commit()

    }

    lateinit var presenter: Presenter
    override fun initUI() {
        initSearch()
        recylerView.layoutManager = GridLayoutManager(this, 2)
    }

    override fun initPresenter() {
        presenter = Presenter()
        presenter.initView(this)
    }

    override fun initSearch() {
        presenter.searchImage()
    }

    override fun passImageData(dataSet: ArrayList<ImageData>) {
        recylerView.adapter = CustomAdapter(dataSet, this)
    }

    override fun getErrorMessage(errorMessage: String) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_fav = findViewById(R.id.btn_fav)
        sharedPreferences = getSharedPreferences("Pref", 0)
        initPresenter()
        initUI()

        btn_fav.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }

    }

}
