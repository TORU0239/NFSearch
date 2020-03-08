package sg.toru.nfsearch.di.module

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sg.toru.nfsearch.data.api.ImageSearchService
import sg.toru.nfsearch.data.api.NetworkUtil
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesOkHttp3(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor:Interceptor
    ):OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .readTimeout(10000, TimeUnit.MILLISECONDS)
            .writeTimeout(10000, TimeUnit.MILLISECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun providesHeaderInterceptor():Interceptor {
        return object: Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val request = original.newBuilder()
                    .addHeader(NetworkUtil.HEADER_KEY_HOST, NetworkUtil.HEADER_VALUE_HOST)
                    .addHeader(NetworkUtil.HEADER_KEY_APIKEY, NetworkUtil.HEADER_VALUE_APIKEY)
                    .method(original.method, original.body)
                    .build()
                return chain.proceed(request)
            }
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient:OkHttpClient):Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkUtil.BASEURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideImageSearchService(retrofit:Retrofit) = retrofit.create(ImageSearchService::class.java)
}