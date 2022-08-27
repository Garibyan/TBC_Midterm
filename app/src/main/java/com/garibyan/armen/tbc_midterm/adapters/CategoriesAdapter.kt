package com.garibyan.armen.tbc_midterm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.garibyan.armen.tbc_midterm.databinding.CaterotyItemBinding
import com.garibyan.armen.tbc_midterm.network.responcemodels.Category

class CategoriesAdapter :
    ListAdapter<Category, CategoriesAdapter.CategoryViewHolder>(CategoryCallBack()) {

    var onItemClickListener: ((category: String) -> Unit)? = null

    inner class CategoryViewHolder(private val binding: CaterotyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.txtCategory.text = category.strCategory
            binding.root.setOnClickListener { onItemClickListener?.invoke(category.strCategory) }
        }
    }

    class CategoryCallBack : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Category, newItem: Category) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoryViewHolder(
            CaterotyItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}