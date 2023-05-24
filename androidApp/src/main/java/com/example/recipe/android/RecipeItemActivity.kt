package com.example.recipe.android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.recipe.android.ui.theme.RecipeTheme
import com.example.recipe.android.ui.theme.Typography

class RecipeItemActivity : ComponentActivity() {

    companion object {
        private const val RECIPE_ID = "recipe_id"
        fun newIntent(context: Context, recipe: Recipe) =
            Intent(context, RecipeItemActivity::class.java).apply {
                putExtra(RECIPE_ID, recipe)
            }
    }

    private val recipe: Recipe by lazy {
        intent?.getSerializableExtra(RECIPE_ID) as Recipe
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeItemView(recipe)
//                    GreetingPreview(recipe)
                }
            }
        }
    }
}

@Composable
fun RecipeItemView(recipe: Recipe) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                state = rememberScrollState(),
                enabled = true
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            contentAlignment = Alignment.TopStart
        ) {
            AsyncImage(
                model = recipe.recipeImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom) {
                Text(
                    text = recipe.title,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        Text(
            text = "Ingredients",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp, bottom = 0.dp, start = 24.dp, end = 24.dp))
        Text(
            text = recipe.ingredients,
            modifier = Modifier.padding(top = 24.dp, bottom = 32.dp, start = 24.dp, end = 24.dp)
        )
        Text(
            text = "Instructions",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp, bottom = 0.dp, start = 24.dp, end = 24.dp))
        Text(
            text = recipe.directions,
            modifier = Modifier.padding(top = 24.dp, bottom = 32.dp, start = 24.dp, end = 24.dp)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun GreetingPreview(recipe: Recipe? = null) {

    RecipeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RecipeItemView(DataProvider.recipeList.get(0))
        }
    }
}