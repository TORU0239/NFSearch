package sg.toru.nfsearch.presentation.main

import android.nfc.tech.NfcA
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import sg.toru.nfsearch.R
import sg.toru.nfsearch.app.NFApp
import sg.toru.nfsearch.data.api.ImageSearchService
import sg.toru.nfsearch.domain.usecaseimp.ImageSearchUseCaseImpl
import sg.toru.nfsearch.domain.viewmodel.MainViewModel
import sg.toru.nfsearch.domain.viewmodel.MainViewModelProvider
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var imageSearchService: ImageSearchService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDependencyInjection()
        viewModel = MainViewModelProvider(ImageSearchUseCaseImpl(imageSearchService)).create(MainViewModel::class.java)
    }

    private fun initDependencyInjection() {
        (application as NFApp).appComponent().injectTo(this)
    }
}