package com.rodriguesporan.usecase.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodriguesporan.usecase.network.getNetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubRepositoryViewModel: ViewModel() {

    private val _githubRepositories: MutableLiveData<List<GithubRepository>> = MutableLiveData<List<GithubRepository>>()
    val githubRepositories get() = _githubRepositories as LiveData<List<GithubRepository>>

    fun listGithubRepositories() {
        getNetworkService().listGithubRepositories().enqueue(object : Callback<List<GithubRepository>> {
            override fun onResponse(call: Call<List<GithubRepository>>, response: Response<List<GithubRepository>>) {
                if (response.isSuccessful) {
                    response.body().also { _githubRepositories.value = it }
                }
            }

            override fun onFailure(call: Call<List<GithubRepository>>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}