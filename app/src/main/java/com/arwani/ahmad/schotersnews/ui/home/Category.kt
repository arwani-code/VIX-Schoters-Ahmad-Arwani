package com.arwani.ahmad.schotersnews.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.arwani.ahmad.schotersnews.R
import com.arwani.ahmad.schotersnews.ui.theme.*

data class Category(
    @DrawableRes val imageCategory: Int,
    @StringRes val textCategory: Int,
    val color: Color
)

val dummyCategory = listOf(
    Category(
        imageCategory = R.drawable.business,
        textCategory = R.string.business_category,
        color = Blue50
    ),
    Category(
        imageCategory = R.drawable.entertainment,
        textCategory = R.string.entertainment_category,
        color = Red200
    ),
    Category(
        imageCategory = R.drawable.health,
        textCategory = R.string.health_category,
        color = Yellow100
    ),
    Category(
        imageCategory = R.drawable.technology,
        textCategory = R.string.technology_category,
        color = Green200
    ),
    Category(
        imageCategory = R.drawable.sports,
        textCategory = R.string.sport_category,
        color = Blue50
    ),
    Category(
        imageCategory = R.drawable.music,
        textCategory = R.string.music_category,
        color = Black100
    )
)