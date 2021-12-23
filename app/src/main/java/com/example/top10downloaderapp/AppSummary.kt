package com.example.top10downloaderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.top10downloaderapp.databinding.ActivityAppSummaryBinding

class AppSummary : AppCompatActivity() {
    private lateinit var binding: ActivityAppSummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppSummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = "App Summary"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.nameTV.text = intent.getStringExtra("appName")
        binding.summaryTV.text = intent.getStringExtra("appSummary")
    }
}