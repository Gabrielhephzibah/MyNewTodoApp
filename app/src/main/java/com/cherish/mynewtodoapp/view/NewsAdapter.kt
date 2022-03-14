package com.cherish.mynewtodoapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cherish.mynewtodoapp.data.model.myData.NewsListData
import com.cherish.mynewtodoapp.databinding.NewsItemLayoutBinding

class NewsAdapter: ListAdapter<NewsListData, NewsAdapter.NewsViewHolder>(PojoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }



inner class NewsViewHolder(var binding:NewsItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(item : NewsListData){
        binding.mtitle.text = item.title
        binding.body.text = item.content
        Glide.with(binding.wrapper).load(item.imageUrl).into(binding.image)
    }

}


object PojoDiffCallback:  DiffUtil.ItemCallback<NewsListData>() {
    override fun areItemsTheSame(oldItem: NewsListData, newItem: NewsListData): Boolean {
        return oldItem.publishedAt == newItem.publishedAt
    }

    override fun areContentsTheSame(oldItem: NewsListData, newItem: NewsListData): Boolean {
       return  oldItem == newItem
    }
}

}


