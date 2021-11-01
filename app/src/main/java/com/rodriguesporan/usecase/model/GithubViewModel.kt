package com.rodriguesporan.usecase.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodriguesporan.usecase.network.getNetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubViewModel: ViewModel() {

    private val _githubUserRepositories: MutableLiveData<List<GithubRepository>> = MutableLiveData<List<GithubRepository>>()
    val githubUserRepositories get() = _githubUserRepositories as LiveData<List<GithubRepository>>

    private val _githubOrgsRepositories: MutableLiveData<List<GithubRepository>> = MutableLiveData<List<GithubRepository>>()
    val githubOrgsRepositories get() = _githubOrgsRepositories as LiveData<List<GithubRepository>>

    private val _githubOrgsMembers: MutableLiveData<List<GithubMember>> = MutableLiveData<List<GithubMember>>()
    val githubOrgsMembers get() = _githubOrgsMembers as LiveData<List<GithubMember>>

    fun listGithubUserRepositories() {
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
    //                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_LONG).show()
                    TODO("Not yet implemented")
                }
        })
    }

    fun listGithubOrgsRepositories() {
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

    fun listGithubOrgsMembers() {
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
}