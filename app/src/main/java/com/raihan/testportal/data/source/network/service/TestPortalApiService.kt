package com.raihan.testportal.data.source.network.service

import com.raihan.testportal.BuildConfig
import com.raihan.testportal.data.source.network.model.UserResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TestPortalApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): UserResponse

    companion object {
        @JvmStatic
        operator fun invoke(): TestPortalApiService {
            val okHttpClient =
                OkHttpClient.Builder()
                    .addInterceptor(
                        Interceptor { chain ->
                            val newRequest =
                                chain.request().newBuilder()
                                    .addHeader(
                                        "Authorization",
                                        "Bearer ${BuildConfig.Bearer}",
                                    )
                                    .build()
                            chain.proceed(newRequest)
                        },
                    ).build()
            val retrofit =
                Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            return retrofit.create(TestPortalApiService::class.java)
        }
    }
}
