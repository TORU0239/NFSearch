package sg.toru.nfsearch.domain.di

import dagger.Subcomponent
import sg.toru.nfsearch.presentation.main.MainActivity
import sg.toru.nfsearch.presentation.main.MainSearchFragment
import sg.toru.nfsearch.presentation.main.MainWebViewFragment

@PerActivity
@Subcomponent(
    modules = [MainDomainModule::class]
)
interface MainDomainComponent {
    fun injectTo(activity:MainActivity)
    fun injectTo(fragment:MainSearchFragment)
    fun injectTo(fragment:MainWebViewFragment)
}