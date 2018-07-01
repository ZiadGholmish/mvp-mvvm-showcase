package com.mystride.presentation.views.phone

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.github.vacxe.phonemask.PhoneMaskManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.mystride.constatns.AppConstant
import com.mystride.data.remote.models.CountryModel
import com.mystride.mystride.R
import com.mystride.presentation.views.country.CountriesCodesActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_sign_up_phone.*

class SignUpPhoneActivity : AppCompatActivity() {

    val CHOOSE_COUNTRY_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_phone)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setPhoneMask()
        openCountriesCodeScreen()
        addPhoneNumberObservable()
    }

    private fun setPhoneMask() {
        PhoneMaskManager()
                .withMask("(###) ###-####")
                .withValueListener { }
                .bindTo(phone_number)
    }

    private fun openCountriesCodeScreen() {
        country_name.setOnClickListener {
            val intent = Intent(this, CountriesCodesActivity::class.java)
            startActivityForResult(intent, CHOOSE_COUNTRY_REQUEST_CODE)
        }
    }

    private fun addPhoneNumberObservable(): Observable<Boolean> {
        phone_number_layout.error = getString(R.string.please_enter_your_mobile_phone_number)
        val phoneNumberObservable = RxTextView
                .textChanges(phone_number)
                .map { inputText -> inputText.length == 14 }
                .distinctUntilChanged()
        phoneNumberObservable.subscribe { isValidPhone ->
            phone_number_layout.isErrorEnabled = !isValidPhone
            btn_continue.isEnabled = isValidPhone
            if (!isValidPhone) {
                phone_number_layout.error = getString(R.string.please_enter_your_mobile_phone_number)
            }
        }
        return phoneNumberObservable
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CHOOSE_COUNTRY_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.let { showSelectedCountry(it.getParcelableExtra(AppConstant.SELECTED_COUNTRY_INTENT_NAME)) }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun showSelectedCountry(selectedCountry: CountryModel) {
        country_name.setText(selectedCountry.name)
        country_code.setText(selectedCountry.dial_code)
    }

}