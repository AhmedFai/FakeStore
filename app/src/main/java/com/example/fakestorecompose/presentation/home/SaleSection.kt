package com.example.fakestorecompose.presentation.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
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
import com.example.fakestorecompose.domain.model.ProductsItem
import com.example.fakestorecompose.domain.model.Rating
import com.example.fakestorecompose.presentation.Dimens.MediumPadding1
import com.example.fakestorecompose.ui.theme.Roboto
import com.example.fakestorecompose.ui.theme.dimens

@Composable
fun SaleSection(
    saleProducts: List<ProductsItem>,
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
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(580.dp)
                .background(color = MaterialTheme.colorScheme.background),
            userScrollEnabled = false,

        ) {
            items(count = saleProducts.size) {
                val products = saleProducts[it]
                SaleSectionItem(saleProducts = products, onClick = { onClick(products) })
            }
        }
    }

}

@Composable
fun SaleSectionItem(
    saleProducts: ProductsItem,
    onClick: (ProductsItem) -> Unit
){
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(10.dp)
            .background(color = MaterialTheme.colorScheme.background)
            .clickable { onClick(saleProducts) }
    ) {
        AsyncImage(
            modifier = Modifier
                .size(180.dp)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(saleProducts.image).build(),
            contentDescription = "Sale Image",
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .height(96.dp)

        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = saleProducts.title,
                fontSize = 13.sp,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                fontFamily = Roboto,
                color = colorResource(id = R.color.body)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = saleProducts.category,
                fontSize = 13.sp,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal),
                fontFamily = Roboto,
                color = colorResource(id = R.color.text_medium)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "MRP: " + "₹ " + saleProducts.price.toString(),
                fontSize = 13.sp,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                fontFamily = Roboto,
                color = colorResource(id = R.color.body)
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewSaleProducts(){
    SaleSectionItem(
        saleProducts = ProductsItem(
            0,
            "Electronics",
            "Hi there",
            painterResource(id = R.drawable.onboarding3).toString(),
            0.0,
            Rating(0, 0.0),
            "This is a watch"),
        onClick = {}
        )
}