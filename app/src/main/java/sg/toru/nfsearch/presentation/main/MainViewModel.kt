package sg.toru.nfsearch.presentation.main

import androidx.lifecycle.ViewModel
import sg.toru.nfsearch.domain.usecase.ImageSearchUseCase

class MainViewModel(usecase: ImageSearchUseCase):ViewModel() {
    init {
        usecase.request()
    }
}