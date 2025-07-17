package com.example.fakestorecompose.presentation.home.common

import androidx.compose.ui.graphics.vector.ImageVector

data class ToolbarAction(
    val icon: ImageVector,
    val contentDescription: String,
    val onClick: () -> Unit
)