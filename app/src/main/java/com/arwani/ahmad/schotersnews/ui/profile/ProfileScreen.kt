package com.arwani.ahmad.schotersnews.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
            contentDescription = stringResource(R.string.foto_profile),
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