package com.rodriguesporan.usecase.model

import androidx.lifecycle.*
import com.rodriguesporan.usecase.network.getNetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GithubViewModel: ViewModel() {

    private val _githubUserRepositories: MutableLiveData<List<GithubRepository>> = MutableLiveData()
    val githubUserRepositories: LiveData<List<GithubRepository>> get() = _githubUserRepositories

    private val _githubOrgsRepositories: MutableLiveData<List<GithubRepository>> = MutableLiveData()
    val githubOrgsRepositories: LiveData<List<GithubRepository>> get() = _githubOrgsRepositories

    private val _githubOrgsMembers: MutableLiveData<List<GithubMember>> = MutableLiveData()
    val githubOrgsMembers: LiveData<List<GithubMember>> get() = _githubOrgsMembers

    private val _githubUser: MutableLiveData<GithubUser> = MutableLiveData()
    val githubUser: LiveData<GithubUser> get() = _githubUser

    val githubFirstUserRepository: LiveData<GithubRepository> = Transformations.map(_githubUserRepositories) { it[0] }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadGithubUserRepositories()
            loadGithubOrgsRepositories()
            loadGithubOrgsMembers()
            loadGithubUser()
        }
    }

    private suspend fun loadGithubUserRepositories() {
        try {
            _githubUserRepositories.value = getNetworkService()
                .listGithubUserRepositories(1000)
        } catch (error: Throwable) {
            _githubUserRepositories.value = null
        }
    }

    private suspend fun loadGithubOrgsRepositories() {
        try {
            _githubOrgsRepositories.value = getNetworkService()
                .listGithubOrgsRepositories("square", 1000)
        } catch (error: Throwable) {
            _githubOrgsRepositories.value = null
        }
    }

    private suspend fun loadGithubOrgsMembers() {
        try {
            _githubOrgsMembers.value = getNetworkService()
                .listGithubOrgsMembers("square", 1000)
        } catch (error: Throwable) {
            _githubOrgsMembers.value = null
        }
    }

    private suspend fun loadGithubUser() {
        try {
            _githubUser.value = getNetworkService().getGithubUser()
        } catch (error: Throwable) {
            _githubUser.value = null
        }
    }
}