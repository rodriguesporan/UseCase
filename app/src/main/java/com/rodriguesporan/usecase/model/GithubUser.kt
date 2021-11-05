package com.rodriguesporan.usecase.model

data class GithubUser(
    val login: String,
    val id: Int,
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val name: String,
    val blog: String,
    val location: String,
    val email: String,
    val hireable: Boolean,
    val bio: String,
    val public_repos: Int,
    val public_gists: Int,
    val followers: Int,
    val following: Int,
    val total_private_repos: Int,
    val owned_private_repos: Int,
)
