package sg.toru.nfsearch.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {


    @Provides
    @Singleton
    fun providesContext() = application.applicationContext
}