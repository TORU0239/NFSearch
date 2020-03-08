package sg.toru.nfsearch.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkUtil {
    private var okHttpClient: OkHttpClient
    private var retrofit: Retrofit
    init {
        okHttpClient = okHttp3()
        retrofit = retrofit(okHttpClient)
    }

    private fun okHttp3(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val headerInterceptor = object: Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val request = original.newBuilder()
                                .addHeader(HEADER_KEY_HOST, HEADER_VALUE_HOST)
                                .addHeader(HEADER_KEY_APIKEY, HEADER_VALUE_APIKEY)
                                .method(original.method, original.body)
                                .build()
                return chain.proceed(request)
            }
        }
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .readTimeout(10000, TimeUnit.MILLISECONDS)
            .writeTimeout(10000, TimeUnit.MILLISECONDS)
            .build()
    }

    private fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun service():ImageSearchService = retrofit.create(ImageSearchService::class.java)

    const val BASEURL = "https://contextualwebsearch-websearch-v1.p.rapidapi.com/api/"
    const val IMAGESEARCHURL = "Search/ImageSearchAPI"

    private val HEADER_KEY_HOST = "x-rapidapi-host"
    private val HEADER_VALUE_HOST = "contextualwebsearch-websearch-v1.p.rapidapi.com"

    private val HEADER_KEY_APIKEY = "x-rapidapi-key"
    private val HEADER_VALUE_APIKEY = "0d1d22588dmsh064436da0035f61p175155jsndd90b4894d9f"
}