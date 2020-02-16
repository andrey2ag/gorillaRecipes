package com.uvita.myapp.modules.recipes

import com.uvita.myapp.models.entities.Recipe
import com.uvita.myapp.modules.baseComponents.BaseView

interface RecipeDetailsView : BaseView {
    fun showRecipe(recipe: Recipe)
}