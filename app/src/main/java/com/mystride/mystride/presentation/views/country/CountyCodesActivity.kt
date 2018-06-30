package com.mystride.mystride.presentation.views.country

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.mystride.mystride.R
import kotlinx.android.synthetic.main.activity_county_codes.*

class CountyCodesActivity : AppCompatActivity(), CountriesCodeController {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_county_codes)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initCountriesRecycler()
        showCountriesCodeList()
    }

    private fun initCountriesRecycler() {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        countries_recycler.layoutManager = linearLayoutManager
    }

    override fun showCountriesCodeList() {
        val countriesCodeAdapter = CountriesCodeAdapter()
        countries_recycler.adapter = countriesCodeAdapter
    }
}
