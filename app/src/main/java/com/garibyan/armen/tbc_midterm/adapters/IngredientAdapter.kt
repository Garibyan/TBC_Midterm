package com.garibyan.armen.tbc_midterm.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.garibyan.armen.tbc_midterm.databinding.CocktailIngredientItemBinding
import com.garibyan.armen.tbc_midterm.network.responcemodels.Ingredient
import com.garibyan.armen.tbc_midterm.utils.extentions.randomColor
import kotlin.random.Random

class IngredientAdapter :
    ListAdapter<Ingredient, IngredientAdapter.IngredientViewHolder>(IngredientCallBack()) {

    var onItemClickListener: ((ingredient: String) -> Unit)? = null

    inner class IngredientViewHolder(private val binding: CocktailIngredientItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredient: Ingredient) = with(binding) {
            txtIngredient.text = ingredient.item
            root.setOnClickListener { onItemClickListener?.invoke(ingredient.item) }
            root.setCardBackgroundColor(Int.randomColor())
        }
    }

    class IngredientCallBack : DiffUtil.ItemCallback<Ingredient>() {
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        IngredientViewHolder(
            CocktailIngredientItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}