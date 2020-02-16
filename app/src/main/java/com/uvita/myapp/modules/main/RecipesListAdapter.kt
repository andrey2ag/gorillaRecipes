package com.uvita.myapp.modules.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.uvita.myapp.R
import com.uvita.myapp.models.entities.Recipe
import java.util.*
import kotlin.collections.ArrayList


class RecipesListAdapter(val handler: RecipeHandler) : RecyclerView.Adapter<RecipeViewHolder>(),
    Filterable {
    private var recipes: List<Recipe> = Collections.emptyList()
    private var allRecipes: List<Recipe> = Collections.emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recipe_view_holder,
                parent,
                false
            ), handler
        )

    fun setRecipesList(recipesList: List<Recipe>) {
        recipes = recipesList
        allRecipes = recipesList.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = recipes.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val results = FilterResults()
                val values = ArrayList<Recipe>()
                if (charSequence.isEmpty()) {
                    results.values = allRecipes
                } else {
                    allRecipes.forEach {
                        if (it.title.contains(charSequence) || it.instructions.contains(charSequence))
                            values.add(it)
                    }
                }
                results.values = values
                results.count = values.size
                return results
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                recipes = filterResults.values as List<Recipe>
                notifyDataSetChanged()
            }
        }
    }

    interface RecipeHandler {
        fun onRecipeSelected(recipe: Recipe)
    }
}