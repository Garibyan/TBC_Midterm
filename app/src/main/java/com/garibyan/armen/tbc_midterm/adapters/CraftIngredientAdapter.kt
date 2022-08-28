package com.garibyan.armen.tbc_midterm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.garibyan.armen.tbc_midterm.databinding.CocktailIngredientItemBinding
import com.garibyan.armen.tbc_midterm.databinding.CraftItemBinding
import com.garibyan.armen.tbc_midterm.network.responcemodels.Ingredient
import com.garibyan.armen.tbc_midterm.utils.extentions.randomColor

class CraftIngredientAdapter: ListAdapter<Ingredient, CraftIngredientAdapter.CraftIngredientViewHolder>(CraftIngredientCallBack()) {

    var onItemClickListener: ((ingredient: String) -> Unit)? = null

    inner class CraftIngredientViewHolder(private val binding: CraftItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredient: Ingredient) = with(binding) {
            ingredientTV.text = ingredient.item
        }
    }

    class CraftIngredientCallBack : DiffUtil.ItemCallback<Ingredient>() {
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CraftIngredientViewHolder(CraftItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: CraftIngredientViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}