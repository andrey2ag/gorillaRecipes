package com.uvita.myapp.modules.repository.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.uvita.myapp.models.entities.Recipe

@Dao
abstract class RecipesDao : BaseDao<Recipe>() {

    @Query("Select * from recipes")
    abstract fun getAll(): LiveData<List<Recipe>>

    @Query("Select * from recipes where id=:id")
    abstract fun find(id: Long): LiveData<Recipe?>
}