package com.example.composebasics.homescreen.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

sealed class HomeDesination(val route:String,val title:String, val icons:ImageVector = Icons.Default.Home){
    object Home:HomeDesination( "home",  "Home",  Icons.Default.Home)
    object Favorite:HomeDesination("favorite","Favorite",Icons.Default.Favorite)
    object Latest:HomeDesination("latest","Latest",Icons.Default.ThumbUp)
}
