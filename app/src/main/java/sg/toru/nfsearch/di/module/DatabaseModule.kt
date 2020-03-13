package sg.toru.nfsearch.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import sg.toru.nfsearch.data.database.NFSearchDatabase
import sg.toru.nfsearch.data.database.SearchDao
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesNFDatabase(context: Context):NFSearchDatabase {
        return NFSearchDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun providesNFDatabaseDao(db:NFSearchDatabase):SearchDao {
        return db.searchDao()
    }
}