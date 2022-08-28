package com.garibyan.armen.tbc_midterm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.garibyan.armen.tbc_midterm.databinding.CaterotyItemBinding
import com.garibyan.armen.tbc_midterm.network.responcemodels.Category
import com.garibyan.armen.tbc_midterm.utils.extentions.randomColor

class CategoriesAdapter :
    ListAdapter<Category, CategoriesAdapter.CategoryViewHolder>(CategoryCallBack()) {

    var onItemClickListener: ((category: String) -> Unit)? = null

    inner class CategoryViewHolder(private val binding: CaterotyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) = with(binding){
            txtCategory.text = category.item
            root.setOnClickListener { onItemClickListener?.invoke(category.item) }
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