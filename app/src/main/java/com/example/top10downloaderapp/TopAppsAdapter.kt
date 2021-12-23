package com.example.top10downloaderapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.top10downloaderapp.databinding.ItemRowBinding
import android.app.Activity

// The "activity" parameter is used for "setOnClickListener" method (line 25)
class TopAppsAdapter(private var apps: List<App>, private val activity: Activity): RecyclerView.Adapter<TopAppsAdapter.TopAppsViewHolder>() {
    class TopAppsViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopAppsViewHolder {
        return TopAppsViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TopAppsViewHolder, position: Int) {
        val app = apps[position]
        holder.binding.apply {
            appNameTV.text = app.appName
        }
        holder.binding.appLL.setOnClickListener {
            (activity as MainActivity).showSummary(app)
        }
    }

    override fun getItemCount() = apps.size

    fun showApps(feed: List<App>) {
        this.apps = feed
        notifyDataSetChanged()
    }
}