package com.example.fakestorecompose.presentation.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.fakestorecompose.R
import com.example.fakestorecompose.domain.model.ProductsItem
import com.example.fakestorecompose.domain.model.Rating
import com.example.fakestorecompose.domain.model.TrendingProducts
import com.example.fakestorecompose.presentation.Dimens.ExtraSmallPadding2
import com.example.fakestorecompose.ui.theme.FakeStoreComposeTheme
import com.example.fakestorecompose.ui.theme.Roboto

@Composable
fun TrendingSection(
    trendingProducts: List<ProductsItem>,
    onClick: (ProductsItem) -> Unit
){

    LazyRow(
        modifier = Modifier
            .fillMaxWidth() ,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(15.dp)
    ){

        items(count = trendingProducts.size){
            val products = trendingProducts[it]
            TrendingItem(trendingProducts = products, onClick = { onClick(products) })
        }

    }


}

@Composable
fun TrendingItem(
    trendingProducts : ProductsItem,
    onClick: (ProductsItem) -> Unit
){
    Card(
        modifier = Modifier
            .width(130.dp)
            .height(220.dp)
            .clickable { onClick(trendingProducts) },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(130.dp),
                model = ImageRequest.Builder(LocalContext.current).data(trendingProducts.image).build(),
                contentDescription = "Trending Image",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(5.dp)
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = trendingProducts.title,
                    fontSize = 13.sp,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                    fontFamily = Roboto,
                    color = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = trendingProducts.category,
                    fontSize = 13.sp,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal),
                    fontFamily = Roboto,
                    color = colorResource(id = R.color.text_medium)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "MRP: " + "₹ " + trendingProducts.price.toString(),
                    fontSize = 13.sp,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                    fontFamily = Roboto,
                    color = colorResource(id = R.color.body)
                )
            }

        }

    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TrendingItemPreview(){
    FakeStoreComposeTheme {
        TrendingItem(trendingProducts = ProductsItem(0, "Electronics", "Hi there", painterResource(
            id = R.drawable.onboarding3
        ).toString(), 0.0, Rating(0, 0.0), "This is a watch"), onClick = {})
    }
}