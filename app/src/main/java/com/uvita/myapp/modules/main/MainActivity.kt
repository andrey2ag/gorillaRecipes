package com.uvita.myapp.modules.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.uvita.myapp.R
import com.uvita.myapp.databinding.ActivityMainBinding
import com.uvita.myapp.models.entities.Recipe
import com.uvita.myapp.modules.baseComponents.BaseActivity
import com.uvita.myapp.modules.recipes.RecipeDetailsActivity

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(), MainView,
    RecipesListAdapter.RecipeHandler {

    override fun getViewModel() = ViewModelProviders.of(this).get(MainViewModel::class.java)

    override fun getToolbarTitle() = R.string.title_activity_main

    override fun initializeDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun setNavigationTitle(title: String) {
        supportActionBar?.let {
            it.title = title
        }
    }

    override fun initRecipesList() {
        // Setup List
        val listAdapter = RecipesListAdapter(this)
        viewModel.recipesModelList.observe(this,
            Observer<List<Recipe>>
            {
                listAdapter.setRecipesList(it)
            })
        with(binding.recipesRecycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                listAdapter.filter.filter(charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable) {}
        })

    }

    override fun onRecipeSelected(recipe: Recipe) {
        RecipeDetailsActivity.start(this, recipe.id)
    }
}