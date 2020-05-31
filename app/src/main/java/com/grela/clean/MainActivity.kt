package com.grela.clean

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.grela.clean.AppModules.injectActivity
import com.grela.clean.model.toCountryViewModelList
import com.grela.domain.model.CountryDomainEntity
import com.grela.presentation.MainPresenter
import com.grela.presentation.MainViewTranslator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainViewTranslator {

    private val presenter: MainPresenter by injectActivity()
    lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onCreate()
        adapter = CountryAdapter {
            Toast.makeText(this, it.continent, Toast.LENGTH_LONG).show()
        }
        countryListView.adapter = adapter
    }

    override fun showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
    }

    override fun showCountry(r: List<CountryDomainEntity>) {
        adapter.updateData(r.toCountryViewModelList())
    }
}