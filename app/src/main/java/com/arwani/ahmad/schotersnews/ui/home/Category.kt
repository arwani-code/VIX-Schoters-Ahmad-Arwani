package com.arwani.ahmad.schotersnews.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.arwani.ahmad.schotersnews.R
import com.arwani.ahmad.schotersnews.ui.theme.*

data class Category(
    @DrawableRes val imageCategory: Int,
    val textCategory: String,
    val color: Color
)

val dummyCategory = listOf(
    Category(
        imageCategory = R.drawable.business,
        textCategory = "Business",
        color = Blue50
    ),
    Category(
        imageCategory = R.drawable.entertainment,
        textCategory = "Entertainment",
        color = Red200
    ),
    Category(
        imageCategory = R.drawable.health,
        textCategory = "Health",
        color = Yellow100
    ),
    Category(
        imageCategory = R.drawable.technology,
        textCategory = "Technology",
        color = Green200
    ),
    Category(
        imageCategory = R.drawable.sports,
        textCategory = "Sports",
        color = Yellow50
    ),
    Category(
        imageCategory = R.drawable.science,
        textCategory = "Science",
        color = Black100
    )
)