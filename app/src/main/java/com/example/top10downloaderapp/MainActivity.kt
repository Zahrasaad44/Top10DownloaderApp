package com.example.top10downloaderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.top10downloaderapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerAdapter: TopAppsAdapter
    private lateinit var feed: List<App>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        feed = listOf()

        recyclerAdapter = TopAppsAdapter(feed)
        binding.topAppsRV.adapter = recyclerAdapter
        binding.topAppsRV.layoutManager = LinearLayoutManager(this)

        binding.getFeedBtn.setOnClickListener {
            showProgressBar(true)
            parseRSS()
        }
    }

    private fun parseRSS() {
        CoroutineScope(IO).launch {
            val data = async {
                val parser = XMLParser()
                parser.parseRSS()
            }.await()
            try {
                withContext(Main){
                    showProgressBar(false)
                    recyclerAdapter.showApps(data)
                }
            } catch (e: Exception){
                Toast.makeText(this@MainActivity, "Couldn't get Top Apps", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun showProgressBar(showProgressBar: Boolean) {
        if (showProgressBar) {
            binding.progressBar.isVisible = true
            binding.topAppsRV.isVisible = false
        } else {
            binding.progressBar.isVisible = false
            binding.topAppsRV.isVisible = true
        }
    }
}