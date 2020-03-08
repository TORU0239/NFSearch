package sg.toru.nfsearch.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sg.toru.nfsearch.domain.usecase.ImageSearchUseCase

class MainViewModel(usecase: ImageSearchUseCase):ViewModel() {
    init {
        usecase.request()
    }
}

class MainViewModelProvider(private val useCase: ImageSearchUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(useCase) as T
    }
}