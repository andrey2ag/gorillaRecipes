package com.uvita.myapp.modules.main

import androidx.lifecycle.LiveData
import com.uvita.myapp.general.MyApp.getEventBus
import com.uvita.myapp.models.entities.Recipe
import com.uvita.myapp.modules.baseComponents.BaseViewModel
import com.uvita.myapp.modules.repository.remote.recipes.RecipesRepository
import org.greenrobot.eventbus.Subscribe

class MainViewModel : BaseViewModel<MainView>() {

    init {
        getEventBus().register(this)
    }

    val recipesModelList: LiveData<List<Recipe>> get() = RecipesRepository().recipes

    override fun onResume() {
        view.initRecipesList()
    }

    override fun onStart() {
        if (!getEventBus().isRegistered(this)) {
            getEventBus().register(this)
        }
    }

    override fun onStop() {
        getEventBus().unregister(this)
    }

    @Subscribe
    fun onSetTitleEvent(event: SetTitleEvent) {
        if (view != null) {
            view.setNavigationTitle(event.title)
        }
    }
}