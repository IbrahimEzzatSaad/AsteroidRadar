package com.udacity.asteroidradar.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.databinding.ItemBinding
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.utils.bindAsteroidStatusImage
import java.util.*
import kotlin.collections.ArrayList


class RecyclerViewAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Asteroid, RecyclerViewAdapter.MyViewHolder>(MyDiffUtil) {

    companion object MyDiffUtil : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem == newItem
        }
    }



    inner class MyViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(asteroid: Asteroid?) {
            binding.asteroid = asteroid
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val offer = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(offer)
        }
        holder.bind(offer)
    }

    class OnClickListener(val clickListener: (offer: Asteroid) -> Unit) {
        fun onClick(offer: Asteroid) = clickListener(offer)
    }


}
