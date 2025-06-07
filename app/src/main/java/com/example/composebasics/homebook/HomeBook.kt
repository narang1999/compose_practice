package com.example.composebasics.homebook

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize().systemBarsPadding(),
            horizontalAlignment = Alignment.Start
        ) {
            var query: String by remember { mutableStateOf("") }
            Image(
                painter = painterResource(R.drawable.app_logo),
                contentDescription = "App logo",
                modifier = Modifier.padding(top = 8.dp)
                    .size(height = 60.dp, width = 120.dp)
                    .clip(RectangleShape),
                contentScale = ContentScale.Fit,
            )
            SearchBar(query, { query = it },)
            BooksList()
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch:()-> Unit = {}
) {
    Box(modifier = Modifier.padding(start = 16.dp,end = 16.dp)) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder =  {Text("Search books")} ,
            singleLine = true,
            modifier = Modifier
                 .fillMaxWidth()
                .padding(start = 12.dp ,end = 12.dp)
                .shadow(6.dp, RoundedCornerShape(16.dp))
                .background(Color.White, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp)),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = { onSearch() }
            ),
            leadingIcon =  {Icon(Icons.Default.Search, contentDescription = null)} ,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFFFFFFF),
                unfocusedContainerColor = Color(0xFFF2F2F2),
                disabledContainerColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            ))
    }
}


@Composable
fun BooksList() {
    LazyRow {
        items(10) {

        }
    }
}

@Composable
fun BookItem(title: String, image: String) {

}

@Preview
@Composable
fun AppPreview() {
    MaterialTheme {
        App()
    }
}