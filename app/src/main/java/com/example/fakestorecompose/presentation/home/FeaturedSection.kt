package com.example.fakestorecompose.presentation.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.fakestorecompose.domain.model.FeaturedProducts
import com.example.fakestorecompose.domain.model.ProductsItem
import com.example.fakestorecompose.domain.model.Rating
import com.example.fakestorecompose.presentation.Dimens.MediumPadding1
import com.example.fakestorecompose.ui.theme.Roboto
import com.example.fakestorecompose.ui.theme.dimens

@Composable
fun FeaturedSection(
    featuredProducts: List<ProductsItem>,
    onClick: (ProductsItem) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimens.small1)
            .background(color = MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 20.dp
        )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(470.dp)
                .background(color = MaterialTheme.colorScheme.background),
            userScrollEnabled = false
        ){
            items(count = featuredProducts.size){
                val products = featuredProducts[it]
                FeaturedSectionItem(featuredProducts = products, onClick = { onClick(products) }, featuredProducts)
            }
        }
    }
}

@Composable
fun FeaturedSectionItem(
    featuredProducts: ProductsItem,
    onClick: (ProductsItem) -> Unit,
    size: List<ProductsItem>,
){

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClick(featuredProducts) }
    ){
        AsyncImage(
            modifier = Modifier
                .size(96.dp)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(featuredProducts.image).build(),
            contentDescription = "Featured Image",
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = MediumPadding1)
                .height(96.dp)

        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = featuredProducts.title,
                fontSize = 13.sp,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                fontFamily = Roboto,
                color = colorResource(id = R.color.body)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = featuredProducts.category,
                fontSize = 13.sp,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal),
                fontFamily = Roboto,
                color = colorResource(id = R.color.text_medium)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "MRP: " + "₹ " + featuredProducts.price.toString(),
                fontSize = 13.sp,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                fontFamily = Roboto,
                color = colorResource(id = R.color.body)
            )
        }
    }
    if (size.indexOf(featuredProducts) != size.size - 1){
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(0.5.dp)
            .background(colorResource(id = R.color.body)))
    }


}

//@Preview(showBackground = true)
//@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
//@Composable
//fun FeaturedSectionItemPreview(){
//    FeaturedSectionItem(featuredProducts = ProductsItem(0, "Electronics", "Hi there", painterResource(
//        id = R.drawable.onboarding3
//    ).toString(), 0.0, Rating(0, 0.0), "This is a watch"), onClick = {},)
//}