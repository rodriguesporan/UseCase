package com.rodriguesporan.usecase.ui

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.rodriguesporan.usecase.R
import com.rodriguesporan.usecase.model.GithubViewModel
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    private val githubViewModel: GithubViewModel by viewModels()
    private lateinit var textViewListUserRepos: TextView
    private lateinit var textViewListOrgsRepos: TextView
    private lateinit var textViewListOrgsMembers: TextView
    private lateinit var textViewStartTime: TextView
    private lateinit var textViewFinishTime: TextView
    private lateinit var textViewDuration: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewListUserRepos = findViewById(R.id.textViewListUserRepos)
        textViewListOrgsRepos = findViewById(R.id.textViewListOrgsRepos)
        textViewListOrgsMembers = findViewById(R.id.textViewListOrgsMembers)
        textViewStartTime = findViewById(R.id.textViewStartTime)
        textViewFinishTime = findViewById(R.id.textViewFinishTime)
        textViewDuration = findViewById(R.id.textViewDuration)

        findViewById<ConstraintLayout>(R.id.rootContainer).setOnClickListener {
            textViewStartTime.text = "0"
            textViewFinishTime.text = "0"
            textViewDuration.text = "0s"
            textViewListUserRepos.text = "Loding..."
            textViewListOrgsRepos.text = "Loding..."
            textViewListOrgsMembers.text = "Loding..."
            githubViewModel.loadLists()
        }

        githubViewModel.githubUserRepositories.observe(this, { repositories ->
            textViewListUserRepos.text = "User repositories > ${repositories.size}"
        })

        githubViewModel.githubOrgsRepositories.observe(this, { repositories ->
            textViewListOrgsRepos.text = "Org repositories > ${repositories.size}"
        })

        githubViewModel.githubOrgsMembers.observe(this, { members ->
            textViewListOrgsMembers.text = "Members > ${members.size}"
        })

        githubViewModel.startDateTime.observe(this, { date ->
            textViewStartTime.text = SimpleDateFormat("HH:mm:ss").format(date)
        })

        githubViewModel.finishDateTime.observe(this, { date ->
            textViewFinishTime.text = SimpleDateFormat("HH:mm:ss").format(date)
        })

        githubViewModel.duration.observe(this, { duration ->
            textViewDuration.text = duration
        })
    }
}