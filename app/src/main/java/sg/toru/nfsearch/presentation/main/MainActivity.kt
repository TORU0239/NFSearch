package sg.toru.nfsearch.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import sg.toru.nfsearch.R
import sg.toru.nfsearch.domain.usecaseimp.ImageSearchUseCaseImpl
import sg.toru.nfsearch.domain.viewmodel.MainViewModel
import sg.toru.nfsearch.domain.viewmodel.MainViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = MainViewModelProvider(ImageSearchUseCaseImpl()).create(MainViewModel::class.java)
    }
}