package com.uvita.myapp.modules.recipes

import androidx.lifecycle.Observer
import com.uvita.myapp.models.entities.Recipe
import com.uvita.myapp.modules.baseComponents.BaseViewModel
import com.uvita.myapp.modules.repository.remote.recipes.RecipesRepository

class RecipeDetailsViewModel : BaseViewModel<RecipeDetailsView>() {

    fun loadRecipeDetails(recipeId: Long){
        var repo = RecipesRepository()
        repo.getRecipe(recipeId).observe(view.currentActivity,
            Observer<Recipe?>
            {
                it?.let {
                    view.showRecipe(it)
                }
            })
    }

    override fun onStart() {
    }

    override fun onStop() {
    }

//    @Subscribe
//    fun onSetTitleEvent(event: SetTitleEvent) {
//        if (view != null) {
//            view.setNavigationTitle(event.title)
//        }
//    }
}