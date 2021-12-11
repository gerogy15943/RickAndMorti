package com.example.rickandmorti.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dotamvvm.R
import com.example.dotamvvm.databinding.CellHeroBinding
import com.example.rickandmorti.models.Characters
import com.squareup.picasso.Picasso

class CharacterAdapter: PagingDataAdapter<Characters, CharacterAdapter.CharacterViewHolder>(
    CharacterComparator()
) {
    class CharacterViewHolder(private val binding: CellHeroBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Characters){
            binding.txtName.text = hero.name
            binding.txtSpecies.text = hero.species
            Picasso.get().load(hero.image).into(binding.imgAvatar)
        }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("hero", getItem(position))
            Navigation.findNavController(holder.itemView).navigate(R.id.action_heroesFragment_to_detailsFragment2, bundle)
        }
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {
        val binding = CellHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    class CharacterComparator: DiffUtil.ItemCallback<Characters>() {
        override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem == newItem
        }

    }
}