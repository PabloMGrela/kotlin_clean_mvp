package com.grela.clean

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.grela.clean.AppModules.injectActivity
import com.grela.domain.interactor.GetCountryUseCase
import com.grela.domain.model.CountryDomainEntity
import com.grela.presentation.MainPresenter
import com.grela.presentation.MainViewTranslator
import org.koin.java.KoinJavaComponent.inject

class MainActivity : AppCompatActivity(), MainViewTranslator {

    val presenter: MainPresenter by injectActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onCreate()
    }

    override fun showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
    }

    override fun showCountry(r: CountryDomainEntity) {
        Toast.makeText(this, r.continent, Toast.LENGTH_LONG).show()
    }
}