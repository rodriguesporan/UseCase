package com.rodriguesporan.usecase.ui

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.rodriguesporan.usecase.GithubRepositoryViewModel
import com.rodriguesporan.usecase.R

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private val githubRepositoryViewModel: GithubRepositoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        findViewById<ConstraintLayout>(R.id.rootContainer).setOnClickListener {
            textView.text = "Loding..."
            githubRepositoryViewModel.listGithubRepositories()
        }

        githubRepositoryViewModel.githubRepositories.observe(this, { repositories ->
            textView.text = "response list size > ${repositories.size}"
        })
    }
}