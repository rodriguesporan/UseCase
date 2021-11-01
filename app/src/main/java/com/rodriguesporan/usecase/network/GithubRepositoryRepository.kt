package com.rodriguesporan.usecase.network

import com.rodriguesporan.usecase.GithubRepository
import retrofit2.Call

class GithubRepositoryRepository(private val githubRepositoryService: GithubRepositoryService) {

    fun list(): Call<List<GithubRepository>> = githubRepositoryService.listGithubRepositories()
}