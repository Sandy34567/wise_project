package com.example.wise_project.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wise_project.Model.Drink
import com.example.wise_project.ViewModel.CoctailViewMode
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.wise_project.ViewModel.SharedViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CategoryScreen(navController: NavHostController, sharedViewModel: SharedViewModel) {
    val coctailViewMode = hiltViewModel<CoctailViewMode>()

    val categories by coctailViewMode.drinklist.collectAsState()
    val searchText by coctailViewMode.searchText.collectAsState()
    var isVisible by remember { mutableStateOf(true) }

    val latestCategory = categories
        ?.takeIf { it.isNotEmpty() }
        ?.filter { it.dateModified != null }
        ?.maxByOrNull {
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                .parse(it.dateModified!!)
        }

    val twentyPercentHeight = with(LocalDensity.current) {
        (LocalDensity.current.density * 100.dp.value).toDp()
    }

    LaunchedEffect(key1 = true) {
        coctailViewMode.setSearchText("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 24.sp)) {
                    append("Let's Have\n")
                }
                withStyle(style = SpanStyle(fontSize = 24.sp)) {
                    append(" a Quality Drink")
                }
            },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        // Search TextField
        OutlinedTextField(
            value = searchText,
            onValueChange = { newValue ->
                coctailViewMode.setSearchText(newValue)
                isVisible = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(8.dp),
            textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            },
            label = {
                Text(
                    text = "Search",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    fontSize = 14.sp
                )
            }
        )

        // Linear layout for "New Restaurant" and "See All" text
        if (isVisible && latestCategory != null) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "New Added",
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "See All",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.clickable {
                        isVisible = false
                    }
                )
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .fillMaxWidth()
                    .height(twentyPercentHeight)
                    .clickable {
                        sharedViewModel.setSelectedDrink(latestCategory)
                        navController.navigate("detailScreen")
                    }
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    Image(
                        painter = rememberImagePainter(data = latestCategory.strDrinkThumb),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp))
                            .aspectRatio(1f) // Maintain aspect ratio
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = latestCategory.strDrink,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    latestCategory.strCategory?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
            }
        }

        // Display the drink items
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround

        ) {
            categories?.let {
                items(it) { drinkItem ->
                    CategoryItem(
                        drinkItem = drinkItem,
                        onItemClick = { selectedDrink ->
                            sharedViewModel.setSelectedDrink(selectedDrink)
                            navController.navigate("detailScreen")
                        }
                    )
                }
            }
        }
    }
}
@Composable
fun CategoryItem(drinkItem: Drink, onItemClick: (Drink) -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(160.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFEEEEEEEE))
            .clickable {
                onItemClick(drinkItem)
            },

        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = rememberImagePainter(data = drinkItem.strDrinkThumb),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = drinkItem.strDrink,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(0.dp, 20.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
