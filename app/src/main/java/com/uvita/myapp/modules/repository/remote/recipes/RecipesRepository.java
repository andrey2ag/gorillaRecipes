package com.uvita.myapp.modules.repository.remote.recipes;

import androidx.lifecycle.LiveData;

import com.uvita.myapp.models.entities.Recipe;
import com.uvita.myapp.modules.repository.local.AppDB;
import com.uvita.myapp.modules.repository.remote.BaseRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.uvita.myapp.general.LogUtil.LOGE;
import static java.net.HttpURLConnection.HTTP_OK;

public class RecipesRepository extends BaseRepository {

    RecipesInterface getRestInterface() {
        return retrofit.create(RecipesInterface.class);
    }

    public LiveData<List<Recipe>> getRecipes() {
        getRestInterface().getRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (response.code() == HTTP_OK) {
                    if (response.body() != null) {
                        AppDB.getInstance()
                                .recipesDao()
                                .insertAll(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                LOGE("ERROR", "Error getting recipes.", t);
            }
        });
        return AppDB.getInstance().recipesDao().getAll();
    }

    public LiveData<Recipe> getRecipe(long recipeId) {
        getRestInterface().getRecipe(recipeId).enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                if (response.code() == HTTP_OK) {
                    if (response.body() != null) {
                        AppDB.getInstance()
                                .recipesDao()
                                .update(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                LOGE("ERROR", "Error getting recipe " + recipeId, t);
            }
        });

        return AppDB.getInstance().recipesDao().find(recipeId);
    }
}
