package com.example.fakestorecompose.presentation.home

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.fakestorecompose.domain.model.BannerSection
import com.example.fakestorecompose.presentation.Dimens.IndicatorSize
import kotlinx.coroutines.delay

@Composable
fun BannerSection(
    images: List<String>?
){

    var currentIndex by remember { mutableStateOf(0) }
    val context = LocalContext.current

    //smooth animation for transition between images

    val animatedIndex by animateIntAsState(
        targetValue = currentIndex,
        animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing)
    )

    LaunchedEffect(key1 = currentIndex) {
        delay(3000L)
        currentIndex = (currentIndex + 1) % images!!.size
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            model = ImageRequest.Builder(context).data(data = images?.get(animatedIndex))
                .crossfade(true)
                .build(),
            contentDescription = "Banner Images",
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            images?.forEachIndexed { index, _ ->

                Box(
                    modifier = Modifier
                        .size(IndicatorSize)
                        .clip(CircleShape)
                        .background(
                            if (index == animatedIndex) Color.White
                            else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                        )
                        .padding(4.dp)
                )

            }

        }
    }
}