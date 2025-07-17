package com.example.fakestorecompose.presentation.details

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.fakestorecompose.R
import com.example.fakestorecompose.domain.model.ProductsItem
import com.example.fakestorecompose.domain.model.Review
import dagger.Lazy
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    product: ProductsItem,
    navigateUp: () -> Unit
) {

    val imageHeight = 300.dp
    val imageHeightPx = with(LocalDensity.current) { imageHeight.roundToPx().toFloat() }
    val lazyListState = rememberLazyListState()

    // Calculate scroll offset
    val scrollOffset = if (lazyListState.firstVisibleItemIndex > 0) {
        imageHeightPx
    } else {
        lazyListState.firstVisibleItemScrollOffset.toFloat().coerceIn(0f, imageHeightPx)
    }
    val toolbarAlpha = (scrollOffset / imageHeightPx).coerceIn(0f, 1f)

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(state = lazyListState) {
            item {

                val images = listOf(
                    "https://fastly.picsum.photos/id/32/4032/3024.jpg?hmac=n7I3OdGszMIwuGcvplNthgBmAxvAZ3rNBBSuDFZaItQ",
                    "https://fastly.picsum.photos/id/57/2448/3264.jpg?hmac=ewraXYesC6HuSEAJsg3Q80bXd1GyJTxekI05Xt9YjfQ",
                    "https://fastly.picsum.photos/id/64/4326/2884.jpg?hmac=9_SzX666YRpR_fOyYStXpfSiJ_edO3ghlSRnH2w09Kg"
                )

                ImageSlider(
                    images = images,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight)
                )
            }

            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = product.title, style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = product.description ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    Text(text = "Price", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "$" + product.price, style = MaterialTheme.typography.bodyMedium,fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(text = "Size", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    SelectSize()
                    Spacer(modifier = Modifier.height(18.dp))
                    Text(text = "Color", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    SelectColor()
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(text = "Reviews", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(20.dp))
                    RatingOverview(
                        averageRating = 4f,
                        totalReviews = 125,
                        rating = mapOf(
                            5 to 40,
                            4 to 30,
                            3 to 15,
                            2 to 10,
                            1 to 5
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    ReviewsSectionPreview()
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Add to Cart", modifier = Modifier.padding(10.dp))
                    }
                    // Add more details here
                }
            }
        }

        // Transparent-to-solid TopAppBar
        TopAppBar(
            title = {
                AnimatedVisibility(visible = toolbarAlpha > 0.6f) {
                    Text(product.title)
                }
            },
            navigationIcon = {
                IconCircle(icon = Icons.AutoMirrored.Filled.ArrowBack, onClick = navigateUp)
            },
            actions = {
                IconCircle(icon = Icons.Default.FavoriteBorder) { /* Wishlist */ }
                IconCircle(icon = Icons.Default.ShoppingCart) { /* Cart */ }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background.copy(alpha = toolbarAlpha)
            )
        )
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun RatingOverview(
    averageRating: Float,
    totalReviews: Int,
    rating: Map<Int, Int>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = String.format("%.1f", averageRating),
                fontSize = 28.sp,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Medium
            )
            Row {
                repeat(5) { index ->
                    Icon(
                        imageVector = if (index < averageRating.roundToInt()) Icons.Default.Star else Icons.Outlined.StarBorder,
                        contentDescription = "Rating Star",
                        tint = Color(0xFFFFC107)
                    )
                }
            }
            Text(
                text = "$totalReviews reviews",
                fontSize = 16.sp,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            (5 downTo 1).forEach { star ->
                val percent = rating[star] ?: 0
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("$star", modifier = Modifier.width(20.dp), style = MaterialTheme.typography.bodySmall)
                    LinearProgressIndicator(
                        progress = percent / 100f,
                        modifier = Modifier
                            .weight(1f)
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = colorResource(id = R.color.black),
                        trackColor = Color.LightGray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("$percent%", style = MaterialTheme.typography.bodySmall)
                }
            }
        }

    }
}


@Composable
fun ReviewsSectionPreview() {
    val reviews = listOf(
        Review(
            name = "Sophia Clark",
            date = "2 months ago",
            rating = 5,
            reviewText = "Absolutely love this dress! The fabric is so soft and comfortable, and the fit is perfect.",
            likes = 25,
            comments = 2,
            profileImage = painterResource(id = R.drawable.testimonials_1)
        ),
        Review(
            name = "Olivia Bennett",
            date = "3 months ago",
            rating = 4,
            reviewText = "Great dress for the price. The colors are vibrant, and it’s very flattering.",
            likes = 18,
            comments = 1,
            profileImage = painterResource(id = R.drawable.testimonials_2)
        )
        // Add more reviews...
    )

    ReviewList(reviews = reviews)
}

@Composable
fun ReviewList(
    reviews: List<Review>
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        reviews.take(2).forEach { review ->
            ReviewItem(review = review)
        }

//        Box(
//            modifier = Modifier.fillMaxWidth(),
//            contentAlignment = Alignment.Center
//        ) {
//            TextButton(onClick = { /* Navigate to All Reviews */ }) {
//                Text("View all reviews")
//            }
//        }
    }
}

@Composable
fun ReviewItem(
    review: Review
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = review.profileImage,
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = review.name, fontWeight = FontWeight.Bold)
                Text(text = review.date, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            repeat(review.rating) {
                Icon(Icons.Filled.Star, contentDescription = null, tint = Color(0xFFFFC107))
            }
            repeat(5 - review.rating) {
                Icon(Icons.Outlined.StarBorder, contentDescription = null, tint = Color(0xFFFFC107))
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = review.reviewText, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Icon(Icons.Outlined.ThumbUp, contentDescription = "Like", tint = Color.Gray)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = review.likes.toString(), color = Color.Gray)
            Spacer(modifier = Modifier.width(16.dp))
            Icon(Icons.Outlined.Comment, contentDescription = "Comment", tint = Color.Gray)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = review.comments.toString(), color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Composable
private fun SelectColor() {
    var selectedColor by remember { mutableStateOf(Color.Yellow) }
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        listOf(Color.Yellow, Color.Blue, Color.Red).forEach { color ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(color)
                    .clickable { selectedColor = color }
            ) {
                if (color == selectedColor) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun SelectSize() {
    var selectedSize by remember { mutableStateOf("M") }
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        listOf("S", "M", "L", "XL").forEach { size ->
            Card(
                modifier = Modifier,
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            if (size == selectedSize) Color.LightGray else Color(
                                0xFFF2F2F2
                            )
                        )
                        .clickable { selectedSize = size }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(text = size, style = MaterialTheme.typography.bodyMedium,fontSize = 16.sp)
                }
            }
        }
    }
}


@Composable
fun ImageSlider(
    images: List<String>,
    modifier: Modifier = Modifier,
    autoScroll: Boolean = false
) {

    val pagerState = rememberPagerState(initialPage = 0) {
        images.size
    }

    // Optional: Auto scroll images
    if (autoScroll && images.size > 1) {
        LaunchedEffect(Unit) {
            while (true) {
                delay(3000)
                val nextPage = (pagerState.currentPage + 1) % images.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Box(modifier = modifier) {
        HorizontalPager(
//            count = images.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) { page ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(images[page])
                    .crossfade(true)
                    .build(),
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        // Dot Indicators at bottom right
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(images.size) { index ->
                val isSelected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .size(if (isSelected) 6.dp else 6.dp)
                        .clip(CircleShape)
                        .background(
                            if (isSelected) Color.White
                            else Color.White.copy(alpha = 0.5f)
                        )
                )
            }
        }

    }

}

@Composable
fun IconCircle(icon: ImageVector, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .size(36.dp)
            .background(
                color = colorResource(id = R.color.icon_background).copy(alpha = 0.7f),
                shape = CircleShape
            )
            .border(0.5.dp, colorResource(id = R.color.icon_border).copy(alpha = 0.6f), CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(icon, contentDescription = null, tint = colorResource(id = R.color.icon_tint))
    }
}
