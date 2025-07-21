package com.example.fakestorecompose.presentation.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.fakestorecompose.R
import com.example.fakestorecompose.domain.model.ItemX
import com.example.fakestorecompose.presentation.Dimens.CategoryItemCardSize
import com.example.fakestorecompose.presentation.Dimens.ExtraSmallPadding2
import com.example.fakestorecompose.ui.theme.FakeStoreComposeTheme
import com.example.fakestorecompose.ui.theme.Roboto
import com.example.fakestorecompose.ui.theme.dimens

@Composable
fun CategoriesSection(
    data: List<ItemX>,
    modifier: Modifier = Modifier,
    onClick: (ItemX) -> Unit
) {

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(MaterialTheme.dimens.small1)
    ) {
        items(count = data.size) {
            val category = data[it]
            CategoryItem(category = category, onClick = { onClick(category) })
        }
    }

}

@Composable
fun CategoryItem(
    category: ItemX,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
    ) {
        AsyncImage(
            modifier = Modifier
                .size(CategoryItemCardSize)
                .clip(CircleShape)
                .border(2.dp, Color.Black, CircleShape),
            model = ImageRequest.Builder(context).data(category.image).build(),
            contentDescription = "Category Image",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
        Text(
            modifier = Modifier,
            text = category.title,
            fontSize = 12.sp,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
            fontFamily = Roboto,
            color = colorResource(id = R.color.body)
        )
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CategoryItemPreview() {
    FakeStoreComposeTheme {
        CategoryItem(category = ItemX(0, "", "Faizan"), onClick = {})
    }
}

