package com.example.wise_project.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.wise_project.Model.Drink
import com.example.wise_project.ViewModel.SharedViewModel

@Composable
fun DrinkDetailsScreen(sharedViewModel: SharedViewModel, navController: NavHostController) {

    val drinkItem by sharedViewModel.selectedDrink.collectAsState()

    LaunchedEffect(key1 = true) {
        Log.d("sandyData", drinkItem.toString())
    }
    drinkItem?.let { drink ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(data = drink.strDrinkThumb),
                contentDescription = null,
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = drink.strDrink,
                fontSize = 24.sp,
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Display ingredients
            val ingredients = createListString(
                listOf(
                    drink.strIngredient1,
                    drink.strIngredient2,
                    drink.strIngredient3,
                    drink.strIngredient4,
                    drink.strIngredient5,
                    drink.strIngredient6,
                    drink.strIngredient7,
                    drink.strIngredient8,
                    drink.strIngredient9,
                    drink.strIngredient10,
                    drink.strIngredient11,
                    drink.strIngredient12,
                )
            )
            Text(
                text = "Ingredients: $ingredients",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Display measures
            val measures = createListString(
                listOf(
                    drink.strMeasure1,
                    drink.strMeasure2,
                    drink.strMeasure3,
                    drink.strMeasure4,
                    drink.strMeasure5,
                    drink.strMeasure6,
                    drink.strMeasure7,
                    drink.strMeasure8,
                    drink.strMeasure9,
                    drink.strMeasure10,
                    drink.strMeasure11,
                    drink.strMeasure12,
                )
            )
            Text(
                text = "Measure: $measures",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Display instructions
            val instructions = createListString(
                listOf(
                    drink.strInstructions,
                    drink.strInstructionsDE,
                    drink.strInstructionsIT
                )
            )
            Text(
                text = "Instructions:\n$instructions",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Back button
            IconButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }
    }
}

private fun createListString(list: List<String?>): String {
    return list.filterNotNull().joinToString(separator = ", ")
}