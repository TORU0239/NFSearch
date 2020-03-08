package sg.toru.nfsearch.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sg.toru.nfsearch.domain.usecase.ImageSearchUseCase

class MainViewModel(usecase: ImageSearchUseCase):ViewModel() {
    init {
        usecase.request()
    }
}