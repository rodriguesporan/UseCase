package com.rodriguesporan.usecase.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rodriguesporan.usecase.R
import com.rodriguesporan.usecase.databinding.ActivityMainBinding
import com.rodriguesporan.usecase.model.GithubViewModel

class MainActivity : AppCompatActivity() {

    private val githubViewModel: GithubViewModel by viewModels()
    // That on VIEW OR DATA BINDING way
     private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        That to setContentView in DEFAULT way
//        setContentView(R.layout.activity_main)

//        That to setContentView in VIEW BINDING way
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

//        That to setContentView in DATA BINDING way
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.githubViewModel = githubViewModel
    }
}