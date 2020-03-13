package sg.toru.nfsearch.domain.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sg.toru.nfsearch.app.NFApp
import sg.toru.nfsearch.data.database.NFSearchDatabase
import sg.toru.nfsearch.data.entity.ApiResponse
import sg.toru.nfsearch.data.entity.SearchResult
import sg.toru.nfsearch.domain.usecase.DatabaseUseCase
import sg.toru.nfsearch.domain.usecase.ImageSearchUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val useCase: ImageSearchUseCase,
    private val databaseUseCase:DatabaseUseCase
):ViewModel() {
    val successResponse = MutableLiveData<List<SearchResult>>()
    val failedResponse = MutableLiveData<String>()
    var loadingProgress = MutableLiveData(false)
    var clearCurrentList = MutableLiveData(false)

    private var job:Job? = null
    var currentPage:Int = -1
    var currentQuery:String = ""


    private fun setLoadingStatus(status:Boolean) {
        loadingProgress.value = status
    }

    // Querying to Server by user's input
    fun request(
        queryName: String,
        pageNumber: Int
    ){
        Log.e("Toru", "queried name:: $queryName, current page: $pageNumber")
        currentPage = pageNumber

        setLoadingStatus(true)
        job = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if(job?.isCancelled!! || job?.isCompleted!!) {
                    job?.cancel()
                }  else {
                    val result = useCase.request(queryName, pageNumber)
                    withContext(Dispatchers.Main) {
                        setLoadingStatus(false)
                        when (result) {
                            is ApiResponse.ApiSuccess -> {
                                result.body.value
                                successResponse.value = result.body.value
                                if (currentQuery != queryName) {
                                    currentQuery = queryName
                                    clearCurrentList.value = true
                                } else {
                                    clearCurrentList.value = false
                                }
                            }
                            is ApiResponse.ApiFailure -> {
                                failedResponse.value = result.errorMessage
                            }
                        }
                    }
                }
            }
        }
    }

    // Stopping job when user exits
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