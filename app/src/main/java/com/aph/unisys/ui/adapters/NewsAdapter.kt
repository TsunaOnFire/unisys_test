package com.aph.unisys.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aph.unisys.R
import com.aph.unisys.data.model.NewModel
import com.aph.unisys.databinding.ItemNewBinding
import com.aph.unisys.domain.model.New
import com.aph.unisys.ui.view.MainActivity
import com.squareup.picasso.Picasso

class NewsAdapter  (private val news: List<New>, private val onClickListener:(New) -> Unit) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_new, parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(news[position],onClickListener)
    }

    override fun getItemCount(): Int = news.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemNewBinding.bind(itemView)
        fun bindItems(new: New,onClickListener:(New) -> Unit) {

            binding.tvTitle.text=new.title
            binding.tvDescription.text=new.description
            Picasso.get().load(new.urlToImage).into(binding.ivNew)

            itemView.setOnClickListener{onClickListener(new)}
        }
    }


}