package com.garibyan.armen.tbc_midterm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.garibyan.armen.tbc_midterm.databinding.CocktailItemBinding
import com.garibyan.armen.tbc_midterm.network.responcemodels.Cocktail

class CocktailsAdapter :
    ListAdapter<Cocktail, CocktailsAdapter.CocktailViewHolder>(CocktailsCallBack()) {

    var onItemClickListener: ((cocktail: Cocktail) -> Unit)? = null

    inner class CocktailViewHolder(private val binding: CocktailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cocktail: Cocktail) = with(binding) {
            imgCocktailImg.load(cocktail.strDrinkThumb) {
                transformations(CircleCropTransformation())
            }
            txtCocktailName.text = cocktail.name

            root.setOnClickListener { onItemClickListener?.invoke(cocktail) }
        }
    }

    class CocktailsCallBack : DiffUtil.ItemCallback<Cocktail>() {
        override fun areItemsTheSame(oldItem: Cocktail, newItem: Cocktail) =
            oldItem.idDrink == newItem.idDrink

        override fun areContentsTheSame(oldItem: Cocktail, newItem: Cocktail) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CocktailViewHolder(
            CocktailItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}