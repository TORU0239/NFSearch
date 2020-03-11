package sg.toru.nfsearch.domain.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.*
import sg.toru.nfsearch.data.entity.ApiResponse
import sg.toru.nfsearch.data.entity.SearchResult
import sg.toru.nfsearch.data.entity.SearchResultWrapper
import sg.toru.nfsearch.domain.usecase.ImageSearchUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(private val useCase: ImageSearchUseCase):ViewModel() {

    val imageQueryLiveData = MutableLiveData<String>()
    val successResponse = MutableLiveData<List<SearchResult>>()
    val failedResponse = MutableLiveData<String>()

    private var job:Job? = null

    // Querying to Server by user's input
    fun request(queryName: String){
        Log.e("Toru", "queried name:: $queryName")
        job = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = useCase.request(queryName)
                withContext(Dispatchers.Main) {
                    when (result) {
                        is ApiResponse.ApiSuccess -> {
                            successResponse.postValue(result.body.value)
                        }
                        is ApiResponse.ApiFailure -> {
                            failedResponse.postValue(result.errorMessage)
                        }
                    }
                }
            }
        }
    }

    fun stop() {
        if(job != null) {
            if (job?.isActive!! && !job?.isCancelled!!) {
                Log.e("Toru", "MainViewModel Stop!!!!")
                job?.cancel()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("Toru", "MainViewModel onCleared")
        job?.cancel()
    }
}