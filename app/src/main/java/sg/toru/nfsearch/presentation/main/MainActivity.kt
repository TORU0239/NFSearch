package sg.toru.nfsearch.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sg.toru.nfsearch.R
import sg.toru.nfsearch.domain.usecase.ImageSearchUseCase
import sg.toru.nfsearch.domain.usecaseimp.ImageSearchUseCaseImpl

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = MainViewModelProvider(ImageSearchUseCaseImpl()).create(MainViewModel::class.java)
    }
}

class MainViewModelProvider(private val useCase: ImageSearchUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(useCase) as T
    }
}