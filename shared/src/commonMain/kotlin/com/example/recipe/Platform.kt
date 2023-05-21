package com.example.recipe

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform