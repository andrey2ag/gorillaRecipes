package com.uvita.myapp.modules.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uvita.myapp.BuildConfig
import com.uvita.myapp.models.entities.Recipe
import com.uvita.myapp.modules.repository.local.dao.RecipesDao

@Database(
    entities = [Recipe::class],
    version = 1
)
abstract class AppDB : RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

    companion object {
        @JvmStatic
        lateinit var instance: AppDB
            private set

        @JvmStatic
        fun createAppDatabase(context: Context) {
            synchronized(AppDB::class) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java, BuildConfig.UVITA_DATABASE_NAME
                )
                    .allowMainThreadQueries()
                    .build()
            }
        }
    }
}
