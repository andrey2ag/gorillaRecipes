package com.uvita.myapp.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
class Recipe(
    @PrimaryKey
    var id: Long = 0,
    var title: String = "",
    var rating: Int = 0,
    var image: String = "",
    var instructions: String = ""
)