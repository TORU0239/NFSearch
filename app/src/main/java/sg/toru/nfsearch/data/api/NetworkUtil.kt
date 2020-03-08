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
                                .addHeader("x-rapidapi-host", "contextualwebsearch-websearch-v1.p.rapidapi.com")
                                .addHeader("x-rapidapi-key", "0d1d22588dmsh064436da0035f61p175155jsndd90b4894d9f")
                                .method(original.method, original.body)
                                .build()
                return chain.proceed(request)
            }
        }
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .connectTimeout(3000, TimeUnit.MILLISECONDS)
            .readTimeout(3000, TimeUnit.MILLISECONDS)
            .writeTimeout(3000, TimeUnit.MILLISECONDS)
            .build()
    }

    private fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    const val BASEURL = "https://contextualwebsearch-websearch-v1.p.rapidapi.com/api/"
    const val IMAGESEARCHURL = "Search/ImageSearchAPI"
}