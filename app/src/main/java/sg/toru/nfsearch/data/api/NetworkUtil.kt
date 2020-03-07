package sg.toru.nfsearch.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkUtil {
    private var okHttpClient: OkHttpClient
    var retrofit: Retrofit
    init {
        okHttpClient = okHttp3()
        retrofit = retrofit(okHttpClient)
    }

    private fun okHttp3(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(3000, TimeUnit.MILLISECONDS)
            .readTimeout(3000, TimeUnit.MILLISECONDS)
            .writeTimeout(3000, TimeUnit.MILLISECONDS)
            .build()
    }

    private fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}