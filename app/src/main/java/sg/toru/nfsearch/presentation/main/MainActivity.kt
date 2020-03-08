package sg.toru.nfsearch.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import sg.toru.nfsearch.R
import sg.toru.nfsearch.app.NFApp
import sg.toru.nfsearch.data.api.ImageSearchService
import sg.toru.nfsearch.di.component.DaggerAppComponent
import sg.toru.nfsearch.domain.di.MainDomainModule
import sg.toru.nfsearch.domain.viewmodel.MainViewModel

import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        (application as NFApp).appComponent()
            .mainDomainComponent(MainDomainModule())
            .injectTo(this)
    }
}