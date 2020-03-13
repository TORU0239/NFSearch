package sg.toru.nfsearch.app

import android.app.Application
import sg.toru.nfsearch.di.component.AppComponent
import sg.toru.nfsearch.di.component.DaggerAppComponent
import sg.toru.nfsearch.di.module.AppModule

class NFApp: Application() {
    private lateinit var appComponent: AppComponent
    fun appComponent() = appComponent

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        appComponent.injectTo(this)
    }
}