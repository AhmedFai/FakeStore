@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.fakestorecompose.presentation.home.common

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.fakestorecompose.presentation.storeNavigator.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar(
    title: String,
    showBackButton: Boolean = false,
    onBackButtonClick: () -> Unit = {},
    actions: List<ToolbarAction> = emptyList()
){
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
                if (title == "Home"){
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Back" )
                    }
                }else{
                    if (showBackButton) {
                        IconButton(onClick = { onBackButtonClick() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                }
        },
        actions = {
                actions.forEach {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(it.icon, contentDescription = it.contentDescription)
                    }
                }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CustomToolbarPreview(){
    CustomToolbar(
        title = "JustHome",
        showBackButton = true,
        actions = listOf(
            ToolbarAction(
                icon = Icons.Default.Search,
                contentDescription = "Search"
            ){},
            ToolbarAction(
                icon = Icons.Default.FavoriteBorder,
                contentDescription = "Search"
            ){},
            ToolbarAction(
                icon = Icons.Default.ShoppingCart,
                contentDescription = "Search"
            ){}
        )
    )
}