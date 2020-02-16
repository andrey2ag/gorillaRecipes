package com.uvita.myapp.modules.repository.remote.recipes;

import com.uvita.myapp.models.entities.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipesInterface {
    @GET("recipes")
    Call<List<Recipe>> getRecipes();

    @GET("recipes/{recipeId}")
    Call<Recipe> getRecipe(@Path("recipeId")  Long id);
}
