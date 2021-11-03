package com.rodriguesporan.usecase.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodriguesporan.usecase.network.getNetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class GithubViewModel: ViewModel() {

    private val _githubUserRepositories: MutableLiveData<List<GithubRepository>> = MutableLiveData<List<GithubRepository>>()
    val githubUserRepositories get() = _githubUserRepositories as LiveData<List<GithubRepository>>

    private val _githubOrgsRepositories: MutableLiveData<List<GithubRepository>> = MutableLiveData<List<GithubRepository>>()
    val githubOrgsRepositories get() = _githubOrgsRepositories as LiveData<List<GithubRepository>>

    private val _githubOrgsMembers: MutableLiveData<List<GithubMember>> = MutableLiveData<List<GithubMember>>()
    val githubOrgsMembers get() = _githubOrgsMembers as LiveData<List<GithubMember>>

    private val _startDateTime: MutableLiveData<Date> = MutableLiveData<Date>()
    val startDateTime get() = _startDateTime as LiveData<Date>

    private val _finishDateTime: MutableLiveData<Date> = MutableLiveData<Date>()
    val finishDateTime get() = _finishDateTime as LiveData<Date>

    private val _duration: MutableLiveData<String> = MutableLiveData<String>()
    val duration get() = _duration as LiveData<String>

    fun loadLists() {
        _startDateTime.value = Date()
        _githubUserRepositories.value = listOf()
        _githubOrgsRepositories.value = listOf()
        _githubOrgsMembers.value = listOf()
        listGithubUserRepositories()
        listGithubOrgsRepositories()
        listGithubOrgsMembers()
    }

    private fun listGithubUserRepositories() {
        getNetworkService().listGithubUserRepositories(1000)
            .enqueue(object : Callback<List<GithubRepository>> {
                override fun onResponse(call: Call<List<GithubRepository>>, response: Response<List<GithubRepository>>) {
                    if (response.isSuccessful) {
                        _githubUserRepositories.value = response.body()
                    } else {
                        _githubUserRepositories.value = listOf()
                    }
                    showTimeDuration()
                }

                override fun onFailure(call: Call<List<GithubRepository>>, t: Throwable) {
    //                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_LONG).show()
                    TODO("Not yet implemented")
                }
        })
    }

    private fun listGithubOrgsRepositories() {
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
                    showTimeDuration()
                }

                override fun onFailure(call: Call<List<GithubRepository>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    private fun listGithubOrgsMembers() {
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
                    showTimeDuration()
                }

                override fun onFailure(call: Call<List<GithubMember>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    private fun showTimeDuration() {
        val userRepositoriesSize = _githubUserRepositories.value?.size ?: 0
        val orgsRepositoriesSize = _githubOrgsRepositories.value?.size ?: 0
        val orgsMembersSize = _githubOrgsMembers.value?.size ?: 0
        if (
            userRepositoriesSize > 0 &&
            orgsRepositoriesSize > 0 &&
            orgsMembersSize > 0
        ) {
            _finishDateTime.value = Date()
            _startDateTime.value?.time?.let {
                val timeDifference = _finishDateTime.value?.time?.minus(it)
                _duration.value = "${timeDifference.toString()}ms"
            }
        }
    }
}