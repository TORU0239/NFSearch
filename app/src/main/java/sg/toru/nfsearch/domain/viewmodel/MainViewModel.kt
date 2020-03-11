package sg.toru.nfsearch.domain.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sg.toru.nfsearch.data.entity.ApiResponse
import sg.toru.nfsearch.data.entity.SearchResult
import sg.toru.nfsearch.domain.usecase.ImageSearchUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(private val useCase: ImageSearchUseCase):ViewModel() {

    val imageQueryLiveData = MutableLiveData<String>()
    val successResponse = MutableLiveData<List<SearchResult>>()
    val failedResponse = MutableLiveData<String>()

    private var job:Job? = null

    // Querying to Server by user's input
    fun request(
        queryName: String,
        pageNumber: Int
    ){
        Log.e("Toru", "queried name:: $queryName")
        job = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = useCase.request(queryName, pageNumber)
                withContext(Dispatchers.Main) {
                    when (result) {
                        is ApiResponse.ApiSuccess -> {
                            successResponse.value = result.body.value
                        }
                        is ApiResponse.ApiFailure -> {
                            failedResponse.value = result.errorMessage
                        }
                    }
                }
            }
        }
    }

    // Stopping job when
    fun stop() {
        job?.let {
            if (it.isActive && !it.isCancelled) {
                it.cancel()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("Toru", "MainViewModel onCleared")
        stop()
    }
}