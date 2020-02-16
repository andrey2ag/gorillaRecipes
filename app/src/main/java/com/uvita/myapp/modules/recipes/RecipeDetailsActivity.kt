package com.uvita.myapp.modules.recipes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import com.uvita.myapp.R
import com.uvita.myapp.databinding.ActivityRecipeDetailsBinding
import com.uvita.myapp.models.entities.Recipe
import com.uvita.myapp.modules.baseComponents.BaseActivity

class RecipeDetailsActivity : BaseActivity<RecipeDetailsViewModel, ActivityRecipeDetailsBinding>(),
    RecipeDetailsView {

    override fun getViewModel() =
        ViewModelProviders.of(this).get(RecipeDetailsViewModel::class.java)

    override fun getToolbarTitle() = R.string.title_activity_main

    override fun initializeDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_details)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadRecipeDetails(intent.getLongExtra(RECIPE_ID, 0))
    }

    override fun showRecipe(recipe: Recipe) {
        setToolbarTitle(recipe.title)
        with(binding) {
            if (recipe.image.isNotEmpty()) {
                Picasso.get().load(recipe.image).into(imageView);
            }
            instructions.text = recipe.instructions
            ratingBar.rating = recipe.rating.toFloat()
            ratingBar.numStars = 5
        }
    }

    companion object {

        var RECIPE_ID = "recipeId"

        @JvmStatic
        fun start(activity: Context, recipeId: Long) {
            val intent = Intent(activity, RecipeDetailsActivity::class.java)
            intent.putExtra(RECIPE_ID, recipeId)
            activity.startActivity(intent)
        }
    }
}
