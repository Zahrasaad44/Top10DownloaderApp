package com.example.top10downloaderapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.top10downloaderapp.databinding.ItemRowBinding

class TopAppsAdapter(var apps: List<App>): RecyclerView.Adapter<TopAppsAdapter.TopAppsViewHolder>() {
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
    }

    override fun getItemCount() = apps.size

    fun showApps(feed: List<App>) {
        this.apps = feed
        notifyDataSetChanged()
    }
}