package com.example.recipe.android

import java.io.Serializable

data class Recipe(
    val id: Int,
    val title: String,
    val prepTime: String,
    val cookTime: String,
    val totalTime: String,
    val servings: String,
    val ingredients: String,
    val directions: String,
    val recipeImage: String
) : Serializable