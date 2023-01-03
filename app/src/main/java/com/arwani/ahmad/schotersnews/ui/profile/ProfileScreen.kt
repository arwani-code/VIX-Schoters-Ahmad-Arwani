package com.arwani.ahmad.schotersnews.ui.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.arwani.ahmad.schotersnews.R
import com.arwani.ahmad.schotersnews.navigation.Screen
import com.arwani.ahmad.schotersnews.ui.theme.Blue200
import com.arwani.ahmad.schotersnews.ui.theme.Blue50
import com.arwani.ahmad.schotersnews.ui.theme.Green100
import com.arwani.ahmad.schotersnews.ui.theme.Yellow100

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, navController: NavHostController) {
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
        Divider(
            modifier = modifier.padding(top = 20.dp, end = 100.dp, bottom = 50.dp),
            color = Blue50,
            startIndent = 100.dp
        )
        NameSection(name = stringResource(id = R.string.name_profile), username = stringResource(id = R.string.name_user))
        NameSection(name = stringResource(id = R.string.username_profile), username = stringResource(
            id = R.string.username_user
        ))
    }
}