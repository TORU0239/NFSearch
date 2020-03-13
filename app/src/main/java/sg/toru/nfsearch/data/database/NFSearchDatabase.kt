package sg.toru.nfsearch.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import sg.toru.nfsearch.data.entity.SearchResult

@Database(
    entities = [SearchResult::class],
    exportSchema = true,
    version = 1
)

abstract class NFSearchDatabase : RoomDatabase() {
    abstract fun searchDao(): SearchDao

    companion object {
        private var INSTANCE: NFSearchDatabase? = null

        fun getDatabase(context: Context): NFSearchDatabase {
            if (INSTANCE == null) {
                synchronized(NFSearchDatabase::class.java) {
                    if (INSTANCE == null) {
                        // Get PhraseRoomDatabase database instance
                        INSTANCE = Room.databaseBuilder(
                                context.applicationContext,
                                NFSearchDatabase::class.java,
                                "nf_search_db"
                            )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}