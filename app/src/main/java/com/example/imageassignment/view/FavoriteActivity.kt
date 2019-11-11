package com.example.imageassignment.view

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imageassignment.R
import com.example.imageassignment.model.ImageData
import com.example.imageassignment.presenter.Presenter
import kotlinx.android.synthetic.main.activity_main.*

class FavoriteActivity : AppCompatActivity(), ViewContract, Listener {

    lateinit var presenter: Presenter
    lateinit var sharedPreferences: SharedPreferences
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
        Toast.makeText(this,errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun createImageDetailFragment(item: ImageData) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, FragmentImageDetails.newInstance(item)).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        sharedPreferences = getSharedPreferences("Pref", 0)
        initPresenter()
        initUI()
    }
}
