package sg.toru.nfsearch

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import sg.toru.nfsearch.data.database.NFSearchDatabase
import sg.toru.nfsearch.data.database.SearchDao

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("sg.toru.nfsearch", appContext.packageName)
    }

    private var dao: SearchDao? = null

    @Before
    fun setup() {
        val database = NFSearchDatabase.getDatabase(InstrumentationRegistry.getInstrumentation().targetContext)
        dao = database.searchDao()
    }

    @Test
    fun getCurrentSavedQuery() {
        runBlocking {
            val searchList = dao?.getSearch("trump")
            assertEquals(false, searchList?.size == 0)
        }
    }

}
