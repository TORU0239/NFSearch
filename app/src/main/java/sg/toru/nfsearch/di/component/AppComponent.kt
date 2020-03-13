package sg.toru.nfsearch.di.component

import dagger.Component
import sg.toru.nfsearch.app.NFApp
import sg.toru.nfsearch.di.module.AppModule
import sg.toru.nfsearch.di.module.DatabaseModule
import sg.toru.nfsearch.di.module.NetworkModule
import sg.toru.nfsearch.domain.di.MainDomainComponent
import sg.toru.nfsearch.domain.di.MainDomainModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DatabaseModule::class])
interface AppComponent {
    fun injectTo(app:NFApp)
    fun mainDomainComponent(mainDomainModule: MainDomainModule):MainDomainComponent
}