package com.arwani.ahmad.schotersnews.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arwani.ahmad.schotersnews.R
import com.arwani.ahmad.schotersnews.ui.theme.Grey100

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category
) {
    Column(
        modifier = modifier
            .width(180.dp)
            .height(230.dp)
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .background(color = Color.Black)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(190.dp)
                .background(color = category.color),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(modifier = modifier.size(100.dp), painter = painterResource(id = category.imageCategory), contentDescription = "Sports")
        }
        Text(text = stringResource(id = category.textCategory), color = Color.White, modifier = modifier.padding(top = 10.dp, start = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemPreview(){
    CategoryItem(category = dummyCategory[0])
}