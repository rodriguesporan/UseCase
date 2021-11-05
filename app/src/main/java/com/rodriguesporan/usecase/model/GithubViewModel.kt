package com.rodriguesporan.usecase.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rodriguesporan.usecase.network.getNetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    fun loadData() {
        loadGithubUserRepositories()
        loadGithubOrgsRepositories()
        loadGithubOrgsMembers()
        loadGithubUser()
    }

    private fun loadGithubUserRepositories() {
        getNetworkService().listGithubUserRepositories(1000)
            .enqueue(object : Callback<List<GithubRepository>> {
                override fun onResponse(call: Call<List<GithubRepository>>, response: Response<List<GithubRepository>>) {
                    if (response.isSuccessful) {
                        _githubUserRepositories.value = response.body()
                    } else {
                        _githubUserRepositories.value = listOf()
                    }
                }

                override fun onFailure(call: Call<List<GithubRepository>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
        })
    }

    private fun loadGithubOrgsRepositories() {
        getNetworkService().listGithubOrgsRepositories("square", 1000)
            .enqueue(object : Callback<List<GithubRepository>> {
                override fun onResponse(
                    call: Call<List<GithubRepository>>,
                    response: Response<List<GithubRepository>>
                ) {
                    if (response.isSuccessful) {
                        _githubOrgsRepositories.value = response.body()
                    } else {
                        _githubOrgsRepositories.value = listOf()
                    }
                }

                override fun onFailure(call: Call<List<GithubRepository>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    private fun loadGithubOrgsMembers() {
        getNetworkService().listGithubOrgsMembers("square", 1000)
            .enqueue(object : Callback<List<GithubMember>> {
                override fun onResponse(
                    call: Call<List<GithubMember>>,
                    response: Response<List<GithubMember>>
                ) {
                    if (response.isSuccessful) {
                        _githubOrgsMembers.value = response.body()
                    } else {
                        _githubOrgsMembers.value = listOf()
                    }
                }

                override fun onFailure(call: Call<List<GithubMember>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    private fun loadGithubUser() {
        getNetworkService().getGithubUser()
                .enqueue(object : Callback<GithubUser>{
                    override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                        if (response.isSuccessful) {
                            _githubUser.value = response.body()
                        } else {
                            _githubUser.value = null
                        }
                    }

                    override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
    }
}