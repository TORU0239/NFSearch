package sg.toru.nfsearch.domain.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import sg.toru.nfsearch.data.api.ImageSearchService
import sg.toru.nfsearch.domain.usecase.ImageSearchUseCase
import sg.toru.nfsearch.domain.usecaseimp.ImageSearchUseCaseImpl
import sg.toru.nfsearch.domain.viewmodel.MainViewModel

@Module
class MainDomainModule {

    @PerActivity
    @Provides
    fun provideMainViewModel(useCase:ImageSearchUseCase): MainViewModel {
        return MainViewModelProvider(useCase).create(MainViewModel::class.java)
    }

    @PerActivity
    @Provides
    fun provideImageSearchUseCase(service: ImageSearchService):ImageSearchUseCase = ImageSearchUseCaseImpl(service)
}

@Suppress("UNCHECKED_CAST")
class MainViewModelProvider(private val useCase: ImageSearchUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(useCase) as T
    }
}