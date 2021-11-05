package com.rodriguesporan.usecase.network

import com.rodriguesporan.usecase.BuildConfig
import com.rodriguesporan.usecase.model.GithubMember
import com.rodriguesporan.usecase.model.GithubRepository
import com.rodriguesporan.usecase.model.GithubUser
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private val service: GithubRepositoryService by lazy {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "token ${BuildConfig.PERSONAL_REPO_USER_ACCESS_TOKEN}")
                .build()

            chain.proceed(request)
        }

    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.GITHUB_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())
        .build()

    retrofit.create(GithubRepositoryService::class.java)
}

fun getNetworkService() = service // TODO: create service factory before use Dependency Injection

interface GithubRepositoryService {
    @GET("user/repos")
    fun listGithubUserRepositories(
        @Query("per_page") pageSize: Int
    ): Call<List<GithubRepository>>

    @GET("orgs/{organization}/repos")
    fun listGithubOrgsRepositories(
        @Path("organization") githubOrganization: String,
        @Query("per_page") pageSize: Int
    ): Call<List<GithubRepository>>

    @GET("orgs/{organization}/members")
    fun listGithubOrgsMembers(
        @Path("organization") githubOrganization: String,
        @Query("per_page") pageSize: Int
    ): Call<List<GithubMember>>

    @GET("user")
    fun getGithubUser(): Call<GithubUser>
}