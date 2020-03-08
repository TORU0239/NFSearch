package sg.toru.nfsearch.domain.di

import dagger.Subcomponent
import sg.toru.nfsearch.di.module.NetworkModule
import sg.toru.nfsearch.presentation.main.MainActivity

@PerActivity
@Subcomponent(
    modules = [MainDomainModule::class]
)
interface MainDomainComponent {
    fun injectTo(activity:MainActivity)
}