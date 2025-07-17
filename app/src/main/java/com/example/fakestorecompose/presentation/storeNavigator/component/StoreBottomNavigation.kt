package com.example.fakestorecompose.presentation.storeNavigator.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fakestorecompose.R
import com.example.fakestorecompose.presentation.Dimens.ExtraSmallPadding2
import com.example.fakestorecompose.presentation.Dimens.IconSize
import com.example.fakestorecompose.ui.theme.FakeStoreComposeTheme
import com.example.fakestorecompose.ui.theme.Roboto
import com.example.fakestorecompose.ui.theme.Rubik
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun StoreBottomNavigation(
    items: List<StoreBottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = selectedItem == index
            NavigationBarItem(
                selected = isSelected,
                onClick = { onItemClick(index) },
                icon = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = if (isSelected) Color.LightGray.copy(alpha = 0.2f) else Color.Transparent,
                                shape = CircleShape // Circular shape for the selected item background
                            )
                            .padding(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
                        )
                    }
                },
                label = {
                    Text(
                        text = item.text,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                        fontSize = 13.sp,
                        fontFamily = Roboto
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent // Prevent any background effect around the whole item
                ),
                alwaysShowLabel = true, // Show label with the icon, adjust based on need
                interactionSource = remember { NoRippleInteractionSource() }
            )
        }
    }

}

data class StoreBottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun StoreBottomNavigationPreview() {
    FakeStoreComposeTheme {
        StoreBottomNavigation(
            items = listOf(
                StoreBottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
                StoreBottomNavigationItem(icon = R.drawable.ic_search, text = "Category"),
                StoreBottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Wishlist"),
                StoreBottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Cart"),
                StoreBottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Profile")
            ),
            selectedItem = 0,
            onItemClick = {}
        )
    }
}

class NoRippleInteractionSource : MutableInteractionSource {
    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {
        // Do nothing
    }

    override fun tryEmit(interaction: Interaction): Boolean {
        return true // Do nothing
    }
}