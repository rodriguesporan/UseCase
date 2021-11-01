package com.rodriguesporan.usecase.network

import com.rodriguesporan.usecase.BuildConfig
import com.rodriguesporan.usecase.GithubRepository
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val service: GithubRepositoryService by lazy {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "token ${BuildConfig.PERSONAL_REPO_USER_ACCESS_TOKEN}")
                .build()

            chain.proceed(request)
        }

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())
        .build()

    retrofit.create(GithubRepositoryService::class.java)
}

fun getNetworkService() = service // TODO: create service factory before use Dependency Injection

interface GithubRepositoryService {
    @GET("user/repos")
    fun listGithubRepositories(): Call<List<GithubRepository>>
}