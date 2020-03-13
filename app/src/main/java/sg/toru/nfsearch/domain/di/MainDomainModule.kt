package sg.toru.nfsearch.domain.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import sg.toru.nfsearch.data.api.ImageSearchService
import sg.toru.nfsearch.data.database.NFSearchDatabase
import sg.toru.nfsearch.domain.usecase.DatabaseUseCase
import sg.toru.nfsearch.domain.usecase.DatabaseUseCaseImpl
import sg.toru.nfsearch.domain.usecase.ImageSearchUseCase
import sg.toru.nfsearch.domain.usecase.ImageSearchUseCaseImpl
import sg.toru.nfsearch.domain.viewmodel.MainViewModel
import javax.inject.Inject

@Module
class MainDomainModule {

    @PerActivity
    @Provides
    fun provideMainViewModel(
        useCase:ImageSearchUseCase,
        databaseUseCase: DatabaseUseCase): MainViewModel {
        return MainViewModelProvider(useCase, databaseUseCase).create(MainViewModel::class.java)
    }

    @PerActivity
    @Provides
    fun provideImageSearchUseCase(service: ImageSearchService):ImageSearchUseCase =
        ImageSearchUseCaseImpl(service)

    @PerActivity
    @Provides
    fun providesDatabaseUseCase(database:NFSearchDatabase):DatabaseUseCase = DatabaseUseCaseImpl(database)
}

@Suppress("UNCHECKED_CAST")
class MainViewModelProvider @Inject constructor(
    private val useCase: ImageSearchUseCase,
    private val databaseUseCase:DatabaseUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(useCase, databaseUseCase) as T
    }
}