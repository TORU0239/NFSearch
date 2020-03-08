package sg.toru.nfsearch.domain.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import sg.toru.nfsearch.domain.usecase.ImageSearchUseCase

class MainViewModel(private val useCase: ImageSearchUseCase):ViewModel() {

    val nameLiveData = MutableLiveData<String>()

    val trigger = Transformations.switchMap(nameLiveData) {
        request(it)
    }

    private fun request(queryName: String):MutableLiveData<String> {
        Log.e("Toru", "queried name:: $queryName")
        useCase.request(queryName)
        return MutableLiveData<String>("test")
    }
}