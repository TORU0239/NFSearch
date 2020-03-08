package sg.toru.nfsearch.di.component

import dagger.Component
import sg.toru.nfsearch.di.module.NetworkModule
import sg.toru.nfsearch.domain.usecase.ImageSearchUseCase
import sg.toru.nfsearch.presentation.main.MainActivity

@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun injectTo(useCase: ImageSearchUseCase)
    fun injectTo(activity:MainActivity)
}