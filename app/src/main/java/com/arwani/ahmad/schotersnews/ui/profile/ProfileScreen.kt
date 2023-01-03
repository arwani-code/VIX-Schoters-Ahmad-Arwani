package com.arwani.ahmad.schotersnews.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arwani.ahmad.schotersnews.R
import com.arwani.ahmad.schotersnews.ui.theme.Blue50

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .padding(top = 80.dp)
                .clip(CircleShape)
                .size(200.dp),
            painter = painterResource(id = R.drawable.photo_profile),
            contentDescription = "foto profile",
            contentScale = ContentScale.Crop
        )
        Divider(modifier = modifier.padding(top = 20.dp, end = 100.dp, bottom = 50.dp), color = Blue50, startIndent = 100.dp)
        NameSection(name = "Name", username = "Ahmad Arwani")
        NameSection(name = "Github Username", username = "arwani-code")
    }
}