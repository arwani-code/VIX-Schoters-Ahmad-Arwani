package com.arwani.ahmad.schotersnews.ui.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arwani.ahmad.schotersnews.ui.theme.Blue50

@Composable
fun NameSection(modifier: Modifier = Modifier, name: String, username: String){
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontSize = 17.sp, color = Color.White, fontWeight = FontWeight.Medium)){
                append(text = "$name : ")
            }
            withStyle(style = SpanStyle(fontSize = 17.sp, fontWeight = FontWeight.Medium, color = Color.White.copy(0.9f))){
                append(text = " $username ")
            }
        }
    )
    Divider(modifier = modifier.padding(top = 20.dp, end = 50.dp, bottom = 25.dp), color = Blue50, startIndent = 50.dp)
}