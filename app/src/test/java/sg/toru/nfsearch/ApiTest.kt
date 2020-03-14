package sg.toru.nfsearch

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sg.toru.nfsearch.data.api.ImageSearchService
import sg.toru.nfsearch.data.api.NetworkUtil
import java.util.concurrent.TimeUnit

class ApiTest {
    private var mockWebServer = MockWebServer()
    private lateinit var apiService: ImageSearchService
    private lateinit var okHttpClient: OkHttpClient

    @Before
    fun setup() {
        mockWebServer.start()

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        okHttpClient = OkHttpClient()
            .newBuilder()
            .readTimeout(3000, TimeUnit.MILLISECONDS)
            .writeTimeout(3000, TimeUnit.MILLISECONDS)
            .addInterceptor(interceptor).build()

        apiService = Retrofit.Builder()
            .baseUrl(NetworkUtil.BASEURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ImageSearchService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun apiServiceTest() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.getImageSearch(query("trump"))
            assert(true) {
                response.isSuccessful
            }
            assert(true) {
                response.body() == null
            }
            assert(true) {
                response.body()?.value?.isNotEmpty()!!
            }
        }
    }

    private fun query(
        query:String,
        pageNumber:Int = 20
    ):HashMap<String, String> {
        val map = HashMap<String,String>()
        map["autoCorrect"] = "false"
        map["safeSearch"] = "false"
        map["q"] = query
        map["pageSize"] = "20"
        map["pageNumber"] = pageNumber.toString()
        return map
    }
}