package com.rodriguesporan.usecase.model

data class GithubRepository(
    val id: Int,
    val node_id: String,
    val name: String,
    val full_name: String,
    val private: Boolean
)