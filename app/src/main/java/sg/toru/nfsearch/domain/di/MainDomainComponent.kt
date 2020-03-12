package sg.toru.nfsearch.domain.di

import dagger.Subcomponent
import sg.toru.nfsearch.presentation.main.activity.MainActivity
import sg.toru.nfsearch.presentation.main.fragment.MainSearchFragment
import sg.toru.nfsearch.presentation.main.fragment.MainWebViewFragment

@PerActivity
@Subcomponent(
    modules = [MainDomainModule::class]
)
interface MainDomainComponent {
    fun injectTo(activity: MainActivity)
    fun injectTo(fragment: MainSearchFragment)
    fun injectTo(fragment: MainWebViewFragment)
}