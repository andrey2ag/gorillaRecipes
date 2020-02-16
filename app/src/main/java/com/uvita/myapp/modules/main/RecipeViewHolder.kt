package com.uvita.myapp.modules.main

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uvita.myapp.databinding.RecipeViewHolderBinding
import com.uvita.myapp.models.entities.Recipe

class RecipeViewHolder(view: View, val handler: RecipesListAdapter.RecipeHandler) :
    RecyclerView.ViewHolder(view) {

    val binding: RecipeViewHolderBinding = DataBindingUtil.bind(view)!!

    fun bind(recipe: Recipe) {
        binding.title.text = recipe.title
        binding.root.setOnClickListener { handler.onRecipeSelected(recipe) }
    }
}